package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.DoubleQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.DoubleVector
import java.math.BigDecimal
import java.math.MathContext

public class DoubleMatrix(
    entries: List<MatrixEntry<Double>>
) : AbstractMatrix<Double, Double, DoubleVector, DoubleMatrix, Double, Double>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<Double, Double, DoubleVector, DoubleMatrix>
        get() = DoubleQuotientField

    override fun maxAbsColumnSumNorm(): Double {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): Double {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): Double {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): Double {
        TODO("Not yet implemented")
    }
}
