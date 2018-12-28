package business;

/*
import ilog.concert.*;
import ilog.cplex.*;
import ilog.opl.*;

public class Teste {
     static final String DATADIR = System.getProperty("user.dir");

     static public void main(String[] args) throws Exception{
       int status = 127;
       try {
         IloOplFactory.setDebugMode(true);
         IloOplFactory oplF = new IloOplFactory();
         IloOplErrorHandler errHandler = oplF.createOplErrorHandler();
         IloOplModelSource modelSource = oplF.createOplModelSource(".\\Primal.mod");
         IloOplSettings settings = oplF.createOplSettings(errHandler);
         IloOplModelDefinition def = oplF.createOplModelDefinition(modelSource,settings);
         IloCplex cplex = oplF.createCplex();
         cplex.setOut(null);
         IloOplModel opl = oplF.createOplModel(def, cplex);
         IloOplDataSource dataSource = oplF.createOplDataSource(".\\Primal.dat");

         opl.addDataSource(dataSource);
         opl.generate();

         if (cplex.solve()){
             System.out.println("OBJECTIVE: " + opl.getCplex().getObjValue());
             opl.postProcess();
             opl.printSolution(System.out);
         }
         else {
             System.out.println("No solution!");
         }
         oplF.end();
         status = 0;
       }
       catch (IloOplException ex) {
         System.err.println("### OPL exception: " + ex.getMessage());
         ex.printStackTrace();
         status = 2;
       } catch (IloException ex) {
         System.err.println("### CONCERT exception: " + ex.getMessage());
         ex.printStackTrace();
         status = 3;
       } catch (Exception ex) {
         System.err.println("### UNEXPECTED UNKNOWN ERROR ...");
         ex.printStackTrace();
         status = 4;
       }
       System.exit(status);
     }
}*/