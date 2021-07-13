package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.BigGaussianQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.BigGaussianVector
import io.github.ltennstedt.finnmath.number.complex.BigComplex
import io.github.ltennstedt.finnmath.number.complex.BigGaussian
import java.math.BigDecimal
import java.math.MathContext

public class BigGaussianMatrix(
    entries: List<MatrixEntry<BigGaussian>>
) : AbstractMatrix<BigGaussian, BigComplex, BigGaussianVector, BigGaussianMatrix, Long, Double>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<BigGaussian, BigComplex, BigGaussianVector, BigGaussianMatrix>
        get() = BigGaussianQuotientField

    override fun maxAbsColumnSumNorm(): Long {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): Long {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): Double {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): Long {
        TODO("Not yet implemented")
    }
}
