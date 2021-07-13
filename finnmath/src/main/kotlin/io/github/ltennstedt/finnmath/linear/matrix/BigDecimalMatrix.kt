package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.BigDecimalQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.BigDecimalVector
import java.math.BigDecimal
import java.math.MathContext

public class BigDecimalMatrix(
    entries: List<MatrixEntry<BigDecimal>>
) : AbstractMatrix<BigDecimal, BigDecimal, BigDecimalVector, BigDecimalMatrix, BigDecimal, BigDecimal>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<BigDecimal, BigDecimal, BigDecimalVector, BigDecimalMatrix>
        get() = BigDecimalQuotientField

    override fun maxAbsColumnSumNorm(): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): BigDecimal {
        TODO("Not yet implemented")
    }
}
