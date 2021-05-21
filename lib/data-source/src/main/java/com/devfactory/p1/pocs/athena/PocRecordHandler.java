/*-
 * #%L
 * athena-example
 * %%
 * Copyright (C) 2019 - 2021 Amazon Web Services
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.devfactory.p1.pocs.athena;

import com.amazonaws.athena.connector.lambda.QueryStatusChecker;
import com.amazonaws.athena.connector.lambda.data.BlockSpiller;
import com.amazonaws.athena.connector.lambda.data.writers.GeneratedRowWriter;
import com.amazonaws.athena.connector.lambda.data.writers.extractors.VarCharExtractor;
import com.amazonaws.athena.connector.lambda.handlers.RecordHandler;
import com.amazonaws.athena.connector.lambda.records.ReadRecordsRequest;

public class PocRecordHandler  extends RecordHandler {
    public PocRecordHandler() {
        super("poc");
    }

    @Override
    protected void readWithConstraint(BlockSpiller blockSpiller, ReadRecordsRequest readRecordsRequest, QueryStatusChecker queryStatusChecker) throws Exception {
        GeneratedRowWriter rowWriter = GeneratedRowWriter.newBuilder(readRecordsRequest.getConstraints())
                .withExtractor("example", (VarCharExtractor) (obj, ds) -> {
                    ds.isSet = obj != null ? 1 : 0;
                    ds.value = obj != null ? obj.toString() : null;
        })
                .build();
        blockSpiller.writeRows((block, num) -> rowWriter.writeRow(block, num, "A") ? 1: 0);
        blockSpiller.writeRows((block, num) -> rowWriter.writeRow(block, num, "B") ? 1: 0);
        blockSpiller.writeRows((block, num) -> rowWriter.writeRow(block, num, "C") ? 1: 0);
        blockSpiller.writeRows((block, num) -> rowWriter.writeRow(block, num, "D") ? 1: 0);
    }

}
