package som.primitives.arithmetic;

import java.math.BigInteger;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.Specialization;

import som.primitives.Primitive;


@GenerateNodeFactory
@Primitive(primitive = "int:divideBy:", selector = "/")
public abstract class DividePrim extends ArithmeticPrim {
  @Specialization
  public final long doLong(final long left, final long right) {
    return left / right;
  }

  @Specialization
  @TruffleBoundary
  public final Object doBigInteger(final BigInteger left, final BigInteger right) {
    BigInteger result = left.divide(right);
    return reduceToLongIfPossible(result);
  }

  @Specialization
  @TruffleBoundary
  public final Object doBigInteger(final BigInteger left, final long right) {
    return doBigInteger(left, BigInteger.valueOf(right));
  }

  @Specialization
  @TruffleBoundary
  public final Object doLong(final long left, final BigInteger right) {
    return doBigInteger(BigInteger.valueOf(left), right);
  }

  @Specialization
  public final Object doLong(final long left, final double right) {
    return (long) (left / right);
  }
}
