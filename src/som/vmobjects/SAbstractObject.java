package som.vmobjects;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.TruffleObject;

import som.vm.NotYetImplementedException;


public abstract class SAbstractObject implements TruffleObject {

  public abstract SClass getSOMClass();

  public abstract boolean isValue();

  public ForeignAccess getForeignAccess() {
    throw new NotYetImplementedException();
  }

  @Override
  public String toString() {
    CompilerAsserts.neverPartOfCompilation();
    SClass clazz = getSOMClass();
    if (clazz == null) {
      return "an Object(clazz==null)";
    }
    return "a " + clazz.getName().getString();
  }
}
