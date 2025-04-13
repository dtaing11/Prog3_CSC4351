package Semant;
import Translate.Exp;
import Types.Type;

public class Semant {
  Env env;
  public Semant(ErrorMsg.ErrorMsg err) {
    this(new Env(err));
  }
  Semant(Env e) {
    env = e;
  }

  public void transProg(Absyn.Absyn tree_head) {
    System.out.println("Implement Type Checker!");
  }

  private void error(int pos, String msg) {
    env.errorMsg.error(pos, msg);
  }

  static final Types.VOID   VOID   = new Types.VOID();
  static final Types.INT    INT    = new Types.INT();
  static final Types.STRING STRING = new Types.STRING();
  static final Types.NIL    NIL    = new Types.NIL();


  private Exp checkInt(ExpTy et, int pos) {
    if (!INT.coerceTo(et.ty))
      error(pos, "integer required");
    return et.exp;
  }

  private Exp checkComparable(ExpTy et, int pos) {
    Type a = et.ty.actual();
    if (!(a instanceof Types.INT
            || a instanceof Types.STRING
            || a instanceof Types.NIL
            || a instanceof Types.RECORD
            || a instanceof Types.ARRAY))
      error(pos, "integer, string, nil, record or array required");
    return et.exp;
  }
  ExpTy transExp(Absyn.Exp e) {
    ExpTy result;

    if (e == null)
      return new ExpTy(translate.Error(), VOID);
    else if (e instanceof Absyn.VarExp)
      result = transExp((Absyn.VarExp)e);
    else if (e instanceof Absyn.NilExp)
      result = transExp((Absyn.NilExp)e);
    else if (e instanceof Absyn.IntExp)
      result = transExp((Absyn.IntExp)e);
    else if (e instanceof Absyn.StringExp)
      result = transExp((Absyn.StringExp)e);
    else if (e instanceof Absyn.CallExp)
      result = transExp((Absyn.CallExp)e);
    else if (e instanceof Absyn.OpExp)
      result = transExp((Absyn.OpExp)e);
    else if (e instanceof Absyn.RecordExp)
      result = transExp((Absyn.RecordExp)e);
    else if (e instanceof Absyn.SeqExp)
      result = transExp((Absyn.SeqExp)e);
    else if (e instanceof Absyn.AssignExp)
      result = transExp((Absyn.AssignExp)e);
    else if (e instanceof Absyn.)
      result = transExp((Absyn.IfElseStatement)e);
    else if (e instanceof Absyn.WhileExp)
      result = transExp((Absyn.WhileExp)e);
    else if (e instanceof Absyn.ForExp)
      result = transExp((Absyn.ForExp)e);
    else if (e instanceof Absyn.BreakExp)
      result = transExp((Absyn.BreakExp)e);
    else if (e instanceof Absyn.LetExp)
      result = transExp((Absyn.LetExp)e);
    else if (e instanceof Absyn.ArrayExp)
      result = transExp((Absyn.ArrayExp)e);
    else throw new Error("Semant.transExp");
    e.type = result.ty;
    return result;
  }

  private Exp checkOrderable(ExpTy et, int pos) {
    Type a = et.ty.actual();
    if (!(a instanceof Types.INT
            || a instanceof Types.STRING))
      error(pos, "integer or string required");
    return et.exp;
  }

}


