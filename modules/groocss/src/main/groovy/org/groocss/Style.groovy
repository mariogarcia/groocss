/*
 * Copyright 2016-2023 The GrooCSS authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.groocss

import groovy.transform.*
import java.util.regex.*

/** Represents a CSS style. */
@CompileStatic
@TupleConstructor
@EqualsAndHashCode
class Style implements CSSPart {

    /** Name of the Style (in camel-case) .*/
    String name

    /** Value for this style, usually a String or Measurement. */
    Object value
    
    String toString() { nameToDashed() + ": $value;" }
    
    private String nameToDashed() {
        toDashed(name)
    }

    static String toDashed(String camelCase) {
        Matcher matcher = Pattern.compile('[A-Z]').matcher(camelCase)
        def result = camelCase
        while (matcher.find()) {
            result = result.replace(matcher.group(), '-' + matcher.group().toLowerCase())
        }
        result
    }

    @Override
    boolean isEmpty() {
        return value == ''
    }
}
