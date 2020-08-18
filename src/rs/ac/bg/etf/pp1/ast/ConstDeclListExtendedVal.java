// generated with ast extension for cup
// version 0.8
// 18/7/2020 17:2:12


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclListExtendedVal extends ConstDeclListExtended {

    private ConstDeclListExtended ConstDeclListExtended;
    private String constName;
    private ConstType ConstType;

    public ConstDeclListExtendedVal (ConstDeclListExtended ConstDeclListExtended, String constName, ConstType ConstType) {
        this.ConstDeclListExtended=ConstDeclListExtended;
        if(ConstDeclListExtended!=null) ConstDeclListExtended.setParent(this);
        this.constName=constName;
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
    }

    public ConstDeclListExtended getConstDeclListExtended() {
        return ConstDeclListExtended;
    }

    public void setConstDeclListExtended(ConstDeclListExtended ConstDeclListExtended) {
        this.ConstDeclListExtended=ConstDeclListExtended;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstType getConstType() {
        return ConstType;
    }

    public void setConstType(ConstType ConstType) {
        this.ConstType=ConstType;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclListExtended!=null) ConstDeclListExtended.accept(visitor);
        if(ConstType!=null) ConstType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclListExtended!=null) ConstDeclListExtended.traverseTopDown(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclListExtended!=null) ConstDeclListExtended.traverseBottomUp(visitor);
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclListExtendedVal(\n");

        if(ConstDeclListExtended!=null)
            buffer.append(ConstDeclListExtended.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclListExtendedVal]");
        return buffer.toString();
    }
}
