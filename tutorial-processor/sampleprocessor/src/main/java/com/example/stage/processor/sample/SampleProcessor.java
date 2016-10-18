/**
 * Copyright 2015 StreamSets Inc.
 *
 * Licensed under the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.stage.processor.sample;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.example.stage.lib.sample.Errors;

import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.FileRef;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.OnRecordErrorException;
import com.streamsets.pipeline.api.base.SingleLaneRecordProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class SampleProcessor extends SingleLaneRecordProcessor {
  private static final Logger LOG = LoggerFactory.getLogger(SampleProcessor.class);
  /**
   * Gives access to the UI configuration of the stage provided by the {@link SampleDProcessor} class.
   */
  public abstract String getConfig();

  /** {@inheritDoc} */
  @Override
  protected List<ConfigIssue> init() {
    // Validate configuration values and open any required resources.
    List<ConfigIssue> issues = super.init();

    if (getConfig().equals("invalidValue")) {
      issues.add(
          getContext().createConfigIssue(
              Groups.SAMPLE.name(), "config", Errors.SAMPLE_00, "Here's what's wrong..."
          )
      );
    }

    // If issues is not empty, the UI will inform the user of each configuration issue in the list.
    return issues;
  }

  /** {@inheritDoc} */
  @Override
  public void destroy() {
    // Clean up any open resources.
    super.destroy();
  }

  /** {@inheritDoc} */
  @Override
  protected void process(Record record, SingleLaneBatchMaker batchMaker) throws StageException {
    LOG.info("Input record: {}", record);

    FileRef fileRef = record.get("/fileRef").getValueAsFileRef();
    Metadata metadata;
    try {
      metadata = ImageMetadataReader.readMetadata(fileRef.createInputStream(getContext(), InputStream.class));
    } catch (ImageProcessingException | IOException e) {
      String filename = record.get("/fileInfo/filename").getValueAsString();
      LOG.info("Exception getting metadata from {}", filename, e);
      throw new OnRecordErrorException(record, Errors.SAMPLE_02, e);
    }

    for (Directory directory : metadata.getDirectories()) {
      LinkedHashMap<String, Field> listMap = new LinkedHashMap<>();

      for (Tag tag : directory.getTags()) {
        listMap.put(tag.getTagName(), Field.create(tag.getDescription()));
      }

      if (directory.hasErrors()) {
        for (String error : directory.getErrors()) {
          LOG.info("ERROR: {}", error);
        }
      }

      record.set("/" + directory.getName(), Field.createListMap(listMap));
    }

    LOG.info("Output record: {}", record);

    batchMaker.addRecord(record);
  }

}