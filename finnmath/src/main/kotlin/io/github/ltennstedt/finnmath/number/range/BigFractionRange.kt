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

package io.github.ltennstedt.finnmath.number.range

import com.google.common.annotations.Beta
import com.google.errorprone.annotations.Immutable
import io.github.ltennstedt.finnmath.number.fraction.BigFraction
import org.apiguardian.api.API
import java.math.BigInteger

/**
 * Immutable implementation of a range for [BigFractions][BigFraction]
 *
 * @property start start
 * @property endInclusive endInclusive
 * @constructor Constructs a BigFractionRange
 * @author Lars Tennstedt
 * @since 0.0.1
 */
@API(status = API.Status.EXPERIMENTAL, since = "0.0.1")
@Beta
@Immutable
public data class BigFractionRange(
    override val start: BigFraction,
    override val endInclusive: BigFraction
) : FinnmathFractionRange<BigInteger, BigFraction, BigFractionRange> {
    public companion object {
        /**
         * Empty BigFractionRange
         *
         * @since 0.0.1
         */
        @JvmField
        public val EMPTY: BigFractionRange = BigFractionRange(BigFraction.ONE, BigFraction.ZERO)
    }
}
