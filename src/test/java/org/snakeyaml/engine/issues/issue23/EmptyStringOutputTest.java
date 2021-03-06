/**
 * Copyright (c) 2018, http://www.snakeyaml.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.snakeyaml.engine.issues.issue23;

import org.junit.jupiter.api.Test;
import org.snakeyaml.engine.v2.api.Dump;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.common.ScalarStyle;
import org.snakeyaml.engine.v2.emitter.Emitter;
import org.snakeyaml.engine.v2.events.DocumentStartEvent;
import org.snakeyaml.engine.v2.events.ImplicitTuple;
import org.snakeyaml.engine.v2.events.ScalarEvent;
import org.snakeyaml.engine.v2.events.StreamStartEvent;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Tag("fast")
public class EmptyStringOutputTest {

    @Test
    void outputEmptyString() {
        Dump dumper = new Dump(DumpSettings.builder().build());
        String output = dumper.dumpToString("");
        assertEquals("''\n", output, "The output must NOT contain ---");
    }

    @Test
    void outputEmptyStringWithExplicitStart() {
        Dump dumper = new Dump(DumpSettings.builder().setExplicitStart(true).build());
        String output = dumper.dumpToString("");
        assertEquals("--- ''\n", output, "The output must contain ---");
    }

    @Test
    void outputEmptyStringWithEmitter() {
        DumpSettings settings = DumpSettings.builder().setExplicitStart(false).build();
        MyWriter writer = new MyWriter();
        Emitter emitter = new Emitter(settings, writer);
        emitter.emit(new StreamStartEvent());
        emitter.emit(new DocumentStartEvent(false, Optional.empty(), new HashMap<>()));
        emitter.emit(new ScalarEvent(Optional.empty(), Optional.empty(), new ImplicitTuple(true, true), "theValue123", ScalarStyle.DOUBLE_QUOTED));
        String output = writer.toString();
        assertEquals("\"theValue123\"", output, "The output must NOT contain ---");
    }

    @Test
    void outputEmptyStringWithEmitterWithDefaultSettings() {
        DumpSettings settings = DumpSettings.builder().build();
        MyWriter writer = new MyWriter();
        Emitter emitter = new Emitter(settings, writer);
        emitter.emit(new StreamStartEvent());
        emitter.emit(new DocumentStartEvent(false, Optional.empty(), new HashMap<>()));
        emitter.emit(new ScalarEvent(Optional.empty(), Optional.empty(), new ImplicitTuple(true, true), "theValue123", ScalarStyle.DOUBLE_QUOTED));
        String output = writer.toString();
        assertEquals("\"theValue123\"", output, "The output must NOT contain ---");
    }
}
