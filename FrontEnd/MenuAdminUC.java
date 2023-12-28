package FrontEnd;

import java.util.Scanner;

import BackEnd.UC;
import BackEnd.Listas.*;
import BackEnd.Professor.Professor;

public class MenuAdminUC {
    static final Scanner in = new Scanner(System.in);
        public static void addUC(ListUC listaUC,ListProfessore listaProf) throws InterruptedException{
            in.nextLine();
            String profNum;
            UC uc = new UC(); 
            Professor prof = new Professor();
            System.out.println("#.....Universidade.do.Minho.....#");
            System.out.println("#...........Gestao.UC...........#");
            System.out.println("# Descricao da UC: ");
            uc.setDesignacao(in.nextLine());
            System.out.println("# Num do Regente da UC: ");
            profNum = in.nextLine();
            /*
             * Nos proximos 2 if`s sera verificado se existe um professor pelo num mecanografico,
             * Caso esse 'Num' esteja associdado a um professor verificamos se o mesmo ja e Regente;
             */
            if(!listaProf.checkNumMec(profNum)){
                System.err.println("ERROR Falha ao encontrar professor");
                Thread.sleep(400);
                return;
            }
            if(!listaProf.isRegente(profNum)){
                System.err.println("ERROR Professor ja e Regente");
                Thread.sleep(400);
                return;
            }
            prof = listaProf.getProfByNum(profNum);
            prof.setCargo("Regente");
            uc.setRegente(listaProf.getProfByNum(profNum));
            System.out.format("# Registada UC: %d , %s , %s\t#\n",uc.getId(),profNum,uc.getDesignacao());
            System.out.println("#...............................#");
            Thread.sleep(800);
            listaUC.adicionar(uc);
    }
    
    public static void gestaoUC(ListUC listaUC,ListProfessore listaProf) throws InterruptedException{
        
        int opcao = 0;
        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#.....Universidade.do.Minho.....#");
            System.out.println("#...........Gestao.UC...........#");
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar UC               #");
            System.out.println("#2. Editar Informaceos UC       #");
            System.out.println("#3. Equipa docente              #");
            System.out.println("#4. Listar UCs                  #");
            System.out.println("#5. Remover UC                  #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.nextInt();

            switch (opcao){
                case 0:
                    break;
                case 1:
                    //Registro de novas UCs;
                    addUC(listaUC,listaProf);
                    break;
                case 4:
                    System.out.println("#..........Universidade.do.Minho..........#");
                    System.out.println("#................Gestao.UC................#");
                    listaUC.listarUC(true); // O boolean server para listar caso seja true, e apenas contar caso seja false
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("#.....Universidade.do.Minho.....#");
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("#..........Eliminar.UC..........#");
                    System.out.println("# Id: ");
                    listaUC.removeUC(in.nextInt());
                    Thread.sleep(800);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        }while(opcao != 0);
    }
}
