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

import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.sdk.ProcessorRunner;
import com.streamsets.pipeline.sdk.RecordCreator;
import com.streamsets.pipeline.sdk.StageRunner;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestSampleProcessor {
  @Test
  @SuppressWarnings("unchecked")
  public void testProcessor() throws StageException {
    ProcessorRunner runner = new ProcessorRunner.Builder(SampleDProcessor.class)
        .addConfiguration("config", "value")
        .addOutputLane("output")
        .build();

    runner.runInit();

    try {
      Record record = RecordCreator.create();
      record.set(Field.create(true));
      StageRunner.Output output = runner.runProcess(Arrays.asList(record));
      Assert.assertEquals(1, output.getRecords().get("output").size());
      Assert.assertEquals(true, output.getRecords().get("output").get(0).get().getValueAsBoolean());
    } finally {
      runner.runDestroy();
    }
  }
}
