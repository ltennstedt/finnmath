/*
 * Copyright 2021 Lars Tennstedt
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

package io.github.ltennstedt.finnmath.linear.builder;

import com.google.common.annotations.Beta;
import io.github.ltennstedt.finnmath.linear.field.GaussianQuotientField;
import io.github.ltennstedt.finnmath.linear.field.QuotientField;
import io.github.ltennstedt.finnmath.linear.matrix.GaussianMatrix;
import io.github.ltennstedt.finnmath.linear.vector.GaussianVector;
import io.github.ltennstedt.finnmath.number.complex.Complex;
import io.github.ltennstedt.finnmath.number.complex.Gaussian;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * Java-style builder for {@link GaussianVector GaussianVectors}
 *
 * @author Lars Tennstedt
 * @since 0.0.1
 */
@API(status = API.Status.EXPERIMENTAL, since = "0.0.1")
@Beta
public final class GaussianVectorJavaBuilder extends
    AbstractVectorJavaBuilder<Gaussian, Complex, GaussianVector, GaussianVectorJavaBuilder> {
    /**
     * Constructor
     *
     * @param size size
     * @throws IllegalArgumentException if {@code size < 1}
     * @since 0.0.1
     */
    public GaussianVectorJavaBuilder(final int size) {
        super(size);
    }

    @Override
    protected @NotNull QuotientField<Gaussian, Complex, GaussianVector, GaussianMatrix> getField() {
        return GaussianQuotientField.INSTANCE;
    }
}
