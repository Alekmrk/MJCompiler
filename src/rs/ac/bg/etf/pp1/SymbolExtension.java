package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

class StructExt {
    static final int Bool = 7474;
}

public class SymbolExtension {
    static Struct boolType = new Struct(StructExt.Bool);


    public static void init() {
        // maybe init of table here
        Tab.insert(Obj.Type, "bool", SymbolExtension.boolType);

    }
}