package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator extends VisitorAdaptor {

    private int varCount;

    private int paramCnt;

    private int mainPc;

    private int constVal;
    private int dubina = 0;

    private boolean dupliraj = true;
    private int index;
    private Obj arr;
    //treba da stoji i poslednji pristup nizu

    private List<Integer> operations = new ArrayList<>();
    private List<String> operations2 = new ArrayList<>();
    private List<Obj> variables = new ArrayList<Obj>();
    private List<String> variables2 = new ArrayList<>();

    public int getMainPc() {
        return mainPc;
    }

    @Override
    public void visit(MethodType methodType) {
        // ovo ne mora ako main moze da bude samo void
        if ("main".equalsIgnoreCase(methodType.getMethName())) {
            mainPc = Code.pc;
        }
        methodType.obj.setAdr(Code.pc);

        // Collect arguments and local variables.
        SyntaxNode methodNode = methodType.getParent();
        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);
        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry.
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(varCnt.getCount() + fpCnt.getCount());
    }

    @Override
    public void visit(MethodVoid methodVoid) {
        if ("main".equalsIgnoreCase(methodVoid.getMethName())) {
            mainPc = Code.pc;
        }
        methodVoid.obj.setAdr(Code.pc);

        // Collect arguments and local variables.
        SyntaxNode methodNode = methodVoid.getParent();
        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);
        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry.
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(varCnt.getCount() + fpCnt.getCount());
    }

    @Override
    public void visit(VarDecl VarDecl) {
        varCount++;
    }

    @Override
    public void visit(FormalParamDecl FormalParam) {
        paramCnt++;
    }

    @Override
    public void visit(ReadStmt readStmt) {

        if (readStmt.getDesignator().obj.getType() == Tab.charType) {
            Code.put(Code.bread);
        } else {
            Code.put(Code.read);
        }

        Code.store(readStmt.getDesignator().obj);

        variables = new ArrayList<>();
        operations = new ArrayList<>();
        variables2 = new ArrayList<>();
    }

    public void visit(PrintStmt printStatement) {

        WithNumber number = printStatement.getWithNumber();
        int num = 1;
        boolean changed = false;
        if (number instanceof WithNumberYes) {
            num = ((WithNumberYes) number).getN1();
            changed = true;
        }

        if (printStatement.getExpr().obj.getType().equals(Tab.charType)) {
            Code.loadConst(num);
            Code.put(Code.bprint);
        } else {
            num = changed ? num : 5;
            Code.loadConst(num);
            Code.put(Code.print);
        }
        variables = new ArrayList<>();
        operations = new ArrayList<>();
        variables2 = new ArrayList<>();
    }

    public void visit(ConstNumber constNumber) {
        if (constNumber.getParent().getClass() == ConstDecl.class || constNumber.getParent().getClass() == ConstDeclListExtendedVal.class) {
            return;
        }
        Obj con = Tab.insert(Obj.Con, "$", constNumber.obj.getType());
        con.setLevel(0);
        con.setAdr(constNumber.getVal());
        Code.load(con);
        constVal = constNumber.getVal();
        //Code.load(new Obj(Obj.Con, "$", Const.struct, Const.getN1(), 0));
        if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
            variables2.add("*");
        }

    }

    public void visit(ConstChar constChar) {
        if (constChar.getParent().getClass() == ConstDecl.class || constChar.getParent().getClass() == ConstDeclListExtendedVal.class) {
            return;
        }
        Obj con = Tab.insert(Obj.Con, "$", constChar.obj.getType());
        con.setLevel(0);
        con.setAdr(constChar.getVal());
        Code.load(con);
        constVal = constChar.getVal();
        //char c;
        //Code.store(constChar.obj);
        //Code.store(Obj o)
        if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
            variables2.add("*");
        }
    }

    public void visit(Assignment assignment) {
        variables = new ArrayList<>();
        operations = new ArrayList<>();
        variables2 = new ArrayList<>();
    }

    public void visit(ReturnStmtExpr assignment) {
        variables = new ArrayList<>();
        operations = new ArrayList<>();
        variables2 = new ArrayList<>();
    }


    public void visit(ConstBool constBool) {
        if (constBool.getParent().getClass() == ConstDecl.class || constBool.getParent().getClass() == ConstDeclListExtendedVal.class) {
            return;
        }
        Obj con = Tab.insert(Obj.Con, "$", constBool.obj.getType());
        con.setLevel(0);// ovde ispisuje kao 1 kada je true i kao 0 kad je false
        int val = constBool.getVal() ? 1 : 0;
        con.setAdr(val);
        Code.load(con);
        constVal = val;
        if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
            variables2.add("*");
        }
    }

    @Override
    public void visit(MethodDecl MethodDecl) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    @Override
    public void visit(EqualDesStatement equalDesStatement) {
        //ulaz pod pretpostavkom da smo variables dobro odredili
        //na kraju ne smemo da guramo promenjive u variables
        int op2 = -10;
        op2 = operations.remove(0);

        while (!variables.isEmpty() && !operations.isEmpty()) {
            Obj temp = variables.remove(variables.size() - 1);
            int ope = operations.remove(operations.size() - 1);
            //zbog designatorIdentExtended onaj deo ja mogu da znam da ako je array da je dupliran
            // tj ide ---- niz 1 ni[1] ----- redom na steku
            //ovo gore treba da se pretumba u var i u var[]
            Code.put(ope);
            if (temp.getName().equals("")) {

                Code.put(Code.dup_x2);
                //Code.put(Code.pop);
            } else {
                Code.put(Code.dup);
            }

            if (temp.getName().equals("")) {
                Code.put(Code.astore);
            } else {
                Code.store(temp);
            }
            if (variables.isEmpty()) {
                //Code.put(Code.dup);
            }
        }
        // vec sam postavio vrednosti za var i za arr[] na vrh steka
        if (op2 >= 0) {
            //sada se na vrhu nalazi expr pa nas objekat

            Code.put(op2);
            //Code.put(Code.dup_x1);
            //Code.put(Code.pop);
            //sada smo zamenili da ide objekat pa na vrhu steka expr

        } else {

            Code.put(Code.dup_x1);
            Code.put(Code.pop);
            Code.put(Code.pop);
        }

        Code.store(equalDesStatement.getDesignator().obj);
        variables = new ArrayList<>();
        operations = new ArrayList<>();
        variables2 = new ArrayList<>();
    }

    @Override
    public void visit(DesignatorIdent designatorIdent) {
        SyntaxNode parent = designatorIdent.getParent();
        //ako maknes ove uslove zakomentarisi onda u plus plus i minus minus( i obrnuto odradi)
        if (EqualDesStatement.class != parent.getClass()) {
            if (DesignatorIdentExtended.class != parent.getClass())
            //&& IncDesStatement.class != parent.getClass() && DecDesStatement.class != parent.getClass())
            {
                //Code.load(designatorIdent.obj);
                // ovde isto ne treba da dodajem ako ne treba da bude deo variables
                SyntaxNode a = designatorIdent;
                boolean var = true;
                while (a.getParent() != null && a.getParent().getClass() != EqualDesStatement.class) {
                    if (a.getParent().getClass() == DesignatorIdentExtended.class) {//dodaj i da je na kraju lanca

                        var = false;
                        break;
                    }
                    a = a.getParent();
                }
                if (var) {

                    a = designatorIdent;
                    boolean AtEnd = true;
                    while (a.getParent() != null && a.getParent().getClass() != EqualDesStatement.class) {
                        //if(a.getParent()instanceof DesignatorFactor){
                        a = a.getParent();
                        if (a instanceof YesAddopTermList) {

                            if (((YesAddopTermList) a).getAddopTermList() instanceof YesAddopTermList) {
                                if (((YesAddopTermList) (((YesAddopTermList) a).getAddopTermList())).getAddop() instanceof AddopBasicRight) {
                                    variables.add(designatorIdent.obj);
                                    variables2.add(designatorIdent.obj.getName());
                                    // ima += posle ovoga
                                } else {
                                    // + -
                                }
                                break;
                            }
                        }
                        if (a instanceof YesMulopFactorList) {

                            if (((YesMulopFactorList) a).getMulopFactorList() instanceof YesMulopFactorList) {
                                if (((YesMulopFactorList) (((YesMulopFactorList) a).getMulopFactorList())).getMulop() instanceof MulopBasicRight) {
                                    variables.add(designatorIdent.obj);
                                    variables2.add(designatorIdent.obj.getName());
                                    // ima *= posle ovoga
                                } else {
                                    // * / %
                                }
                                break;
                            }
                        }
                        if (a instanceof TermFactor) {

                            if (((TermFactor) a).getMulopFactorList() instanceof YesMulopFactorList) {
                                if (((YesMulopFactorList) (((TermFactor) a).getMulopFactorList())).getMulop() instanceof MulopBasicRight) {
                                    variables.add(designatorIdent.obj);
                                    variables2.add(designatorIdent.obj.getName());
                                    // ima *= posle ovoga
                                } else {
                                    // * / %
                                }
                                break;
                            }
                        }
                        if (a instanceof AddExpr) {

                            if (((AddExpr) a).getAddopTermList() instanceof YesAddopTermList) {
                                if (((YesAddopTermList) (((AddExpr) a).getAddopTermList())).getAddop() instanceof AddopBasicRight) {
                                    variables.add(designatorIdent.obj);
                                    variables2.add(designatorIdent.obj.getName());
                                    // ima += posle ovoga
                                } else {
                                    // + -
                                }
                                break;
                            }
                        }

                    }
                }
                //Code.load(designatorIdent.obj);
            }
        }

        Code.load(designatorIdent.obj);
    }

    @Override
    public void visit(DesignatorIdentExtended designatorIdentExtended) {
        SyntaxNode parent = designatorIdentExtended.getParent();


        //ako maknes ove uslove zakomentarisi onda u plus plus i minus minus( i obrnuto odradi)
        if (EqualDesStatement.class != parent.getClass())//&& IncDesStatement.class != parent.getClass() && DecDesStatement.class != parent.getClass())
        {

            if (IncDesStatement.class == parent.getClass() || DecDesStatement.class == parent.getClass()) {

                Code.put(Code.dup2);
            } else {

                //ovo ne treba ako je print u pitanju print ili dodela(sa desne strane je)
                //ovo ne sme ako je izracunavanje expr u pitanju
                //Code.put(Code.dup2);
                //ovo  Code.put(Code.dup2); treba samo ako je najlevlji u *= += itd...
                // ukoliko je najlevlji i ukoliko nije poslednji u lancu treba da napisem dup2
                // i da stavim u variables
                SyntaxNode a = designatorIdentExtended;
                boolean var = true;
                while (a.getParent() != null && a.getParent().getClass() != EqualDesStatement.class) {
                    if (a.getParent().getClass() == DesignatorIdentExtended.class) {//dodaj i da je na kraju lanca

                        var = false;
                        break;
                    }
                    a = a.getParent();
                }
                if (var) {

                    a = designatorIdentExtended;
                    boolean AtEnd = true;
                    while (a.getParent() != null && a.getParent().getClass() != EqualDesStatement.class) {
                        //if(a.getParent()instanceof DesignatorFactor){
                        a = a.getParent();
                        if (a instanceof YesAddopTermList) {

                            if (((YesAddopTermList) a).getAddopTermList() instanceof YesAddopTermList) {
                                if (((YesAddopTermList) (((YesAddopTermList) a).getAddopTermList())).getAddop() instanceof AddopBasicRight) {
                                    Code.put(Code.dup2);
                                    variables.add(designatorIdentExtended.obj);
                                    variables2.add(designatorIdentExtended.obj.getName());
                                    // ima += posle ovoga
                                } else {
                                    // + -
                                }
                                break;
                            }
                        }
                        if (a instanceof YesMulopFactorList) {

                            if (((YesMulopFactorList) a).getMulopFactorList() instanceof YesMulopFactorList) {
                                if (((YesMulopFactorList) (((YesMulopFactorList) a).getMulopFactorList())).getMulop() instanceof MulopBasicRight) {
                                    Code.put(Code.dup2);
                                    variables.add(designatorIdentExtended.obj);
                                    variables2.add(designatorIdentExtended.obj.getName());
                                    // ima *= posle ovoga
                                } else {
                                    // * / %
                                }
                                break;
                            }
                        }
                        if (a instanceof TermFactor) {

                            if (((TermFactor) a).getMulopFactorList() instanceof YesMulopFactorList) {
                                if (((YesMulopFactorList) (((TermFactor) a).getMulopFactorList())).getMulop() instanceof MulopBasicRight) {
                                    Code.put(Code.dup2);
                                    variables.add(designatorIdentExtended.obj);
                                    variables2.add(designatorIdentExtended.obj.getName());
                                    // ima *= posle ovoga
                                } else {
                                    // * / %
                                }
                                break;
                            }
                        }
                        if (a instanceof AddExpr) {

                            if (((AddExpr) a).getAddopTermList() instanceof YesAddopTermList) {
                                if (((YesAddopTermList) (((AddExpr) a).getAddopTermList())).getAddop() instanceof AddopBasicRight) {
                                    Code.put(Code.dup2);
                                    variables.add(designatorIdentExtended.obj);
                                    variables2.add(designatorIdentExtended.obj.getName());
                                    // ima += posle ovoga
                                } else {
                                    // + -
                                }
                                break;
                            }
                        }

                    }
                }
            }
            Code.load(designatorIdentExtended.obj);
        } else {//otac mu je equalDesStatement

            Code.put(Code.dup2);

            Code.load(designatorIdentExtended.obj);
        }

    }

    public void visit(IncDesStatement incDesStatement) {
        //Code.load(incDesStatement.getDesignator().obj);
        //if(incDesStatement.getParent().getClass()==)

        Code.loadConst(1);
        Code.put(Code.add);
        Code.store(incDesStatement.getDesignator().obj);
    }

    public void visit(DecDesStatement decDesStatement) {
        //Code.load(decDesStatement.getDesignator().obj);
        Code.loadConst(-1);
        Code.put(Code.add);
        Code.store(decDesStatement.getDesignator().obj);
    }

    public void visit(YesMinus yesMinus) {
        Code.put(Code.neg);
    }

    public void visit(YesAddopTermList yesAddopTermList) {

        if (yesAddopTermList.getAddop() instanceof AddopBasicLeft) {

            if ((((AddopBasicLeft) yesAddopTermList.getAddop())).getAddopLeft() instanceof AddopLeftPlus) {
                Code.put(Code.add);
                if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
                    variables2.add("+");
                }
            } else {
                Code.put(Code.sub);
                if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
                    variables2.add("+");
                }
            }
            //variables.clear();
        }/* else if (yesAddopTermList.getAddop() instanceof AddopBasicRight) {
            if ((((AddopBasicRight) yesAddopTermList.getAddop())).getAddopRight() instanceof AddopRightPlusEqual) {
                //Code.put(Code.add); plus equal
                //Code.put(Code.add);
                //Code.put(Code.dup);
                operations.add(Code.add);
                operations2.add("plus");
            } else {
                //Code.put(Code.sub); minus equal
                operations.add(Code.sub);
                operations2.add("minus");

            }
        }*/
        //else if(expression.getAddop() instanceof Minus) Code.put(Code.sub);
    }

    public void visit(YesMulopFactorList yesMulopFactorList) {

        if (yesMulopFactorList.getMulop() instanceof MulopBasicLeft) {
            if ((((MulopBasicLeft) yesMulopFactorList.getMulop())).getMulopLeft() instanceof MulopLeftMul) {
                Code.put(Code.mul);
                if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
                    variables2.add("+");
                }
            } else if ((((MulopBasicLeft) yesMulopFactorList.getMulop())).getMulopLeft() instanceof MulopLeftDiv) {
                Code.put(Code.div);
                if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
                    variables2.add("+");
                }
            } else {
                Code.put(Code.rem);
                if (!variables2.isEmpty() && !variables2.get(variables2.size() - 1).equals("+")) {
                    variables2.add("+");
                }
            }
            //variables.clear();
        }/* else if (yesMulopFactorList.getMulop() instanceof MulopBasicRight) {
            if ((((MulopBasicRight) yesMulopFactorList.getMulop())).getMulopRight() instanceof MulopRightMulEqual) {
                //Code.put(Code.add); mul equal
                operations.add(Code.mul);
                operations2.add("mul");
            } else if ((((MulopBasicRight) yesMulopFactorList.getMulop())).getMulopRight() instanceof MulopRightDivEqual) {
                operations.add(Code.div);
                operations2.add("div");
                //Code.put(Code.sub); div equal
            } else {
                operations.add(Code.rem);
                operations2.add("rem");
                //mod equal
            }
        }*/
    }

    public void visit(AssignopEqual assignopEqual) {
        operations.add(-10);
        operations2.add("nothing");
    }

    public void visit(AddopRightPlusEqual addopRightPlusEqual) {
        operations.add(Code.add);
        operations2.add("plus");
    }

    public void visit(AddopRightMinusEqual addopRightMinusEqual) {
        operations.add(Code.sub);
        operations2.add("minus");
    }

    public void visit(MulopRightDivEqual mulopRightDivEqual) {
        operations.add(Code.div);
        operations2.add("div");
    }

    public void visit(MulopRightMulEqual mulopRightMulEqual) {
        operations.add(Code.mul);
        operations2.add("mul");
    }

    public void visit(MulopRightModEqual mulopRightModEqual) {
        operations.add(Code.rem);
        operations2.add("mod");
    }

    public void visit(NewTypeExpr newTypeExpr) {
        int b = newTypeExpr.getType().obj.getType() == Tab.intType ? 1 : 0;
        Code.put(Code.newarray);
        Code.put(b);
    }


}

