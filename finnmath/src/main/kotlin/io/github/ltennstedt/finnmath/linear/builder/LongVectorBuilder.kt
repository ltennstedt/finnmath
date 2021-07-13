/*
 * Copyright 2020 Lars Tennstedt
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

package io.github.ltennstedt.finnmath.linear.builder

import io.github.ltennstedt.finnmath.linear.field.LongQuotientField
import io.github.ltennstedt.finnmath.linear.vector.LongVector
import io.github.ltennstedt.finnmath.linear.vector.VectorEntry

/**
 * Provides longVector block
 *
 * @since 0.0.1
 */
public fun longVector(init: LongVectorBuilder.() -> Unit): LongVector {
    val builder = LongVectorBuilder()
    builder.init()
    return builder.build()
}

/**
 * Builder for [LongVectors][LongVector]
 *
 * @constructor Constructs a LongVectorBuilder
 * @author Lars Tennstedt
 * @since 0.0.1
 */
public class LongVectorBuilder : AbstractVectorBuilder<Long, LongVector>() {
    override var computationOfAbsent: (Int) -> Long = { _ -> 0L }
    override val vectorConstructor: (l: List<VectorEntry<Long>>) -> LongVector
        get() = LongQuotientField.vectorConstructor
}
