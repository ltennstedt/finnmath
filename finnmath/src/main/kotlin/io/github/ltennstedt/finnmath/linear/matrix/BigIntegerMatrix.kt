package io.github.ltennstedt.finnmath.linear.matrix

import io.github.ltennstedt.finnmath.linear.field.BigIntegerQuotientField
import io.github.ltennstedt.finnmath.linear.field.QuotientField
import io.github.ltennstedt.finnmath.linear.vector.BigIntegerVector
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

public class BigIntegerMatrix(
    entries: List<MatrixEntry<BigInteger>>
) : AbstractMatrix<BigInteger, BigDecimal, BigIntegerVector, BigIntegerMatrix, BigInteger, BigDecimal>(
    entries
) {
    override val isInvertible: Boolean
        get() = TODO("Not yet implemented")
    override val quotientField: QuotientField<BigInteger, BigDecimal, BigIntegerVector, BigIntegerMatrix>
        get() = BigIntegerQuotientField

    override fun maxAbsColumnSumNorm(): BigInteger {
        TODO("Not yet implemented")
    }

    override fun maxAbsRowSumNorm(): BigInteger {
        TODO("Not yet implemented")
    }

    override fun frobeniusNormPow2(): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun frobeniusNorm(mathContext: MathContext): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun maxNorm(): BigInteger {
        TODO("Not yet implemented")
    }
}
