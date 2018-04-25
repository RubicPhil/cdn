package com.cacheserverdeploy.deploy;


import com.xd.algorithm.GA;
import com.xd.algorithm.MyGA;
import com.xd.algorithm.PathCost;
import com.xd.data.GraphProcess;
import com.xd.graph.Graph;
import com.xd.myutils.StringsUtils;

import java.util.List;

public class Deploy
{
    /**
     * todo
     * 
     * @param graphContent caseinfo
     * @return  caseouput info
     * @see [huawei]
     */
    public static String[] deployServer(String[] graphContent)
    {
        /**do your work here**/



        List list = null;
        Graph graph = StringsUtils.readStrings(graphContent);
        GraphProcess graphProcess = new GraphProcess(graph);
        graphProcess.updateGraph();

        int popSize = (int) Math.round(graph.aliveNetVerticesNum * 0.5);
        if (popSize % 2 ==0)
            popSize ++;

//        System.out.println("===");
//        System.out.println(graph.aliveNetVerticesNum);
//        System.out.println(popSize);

        double[] pm2 = {0.003, 0.005, 0.007, 0.01,0.03};
        double[] pm0 = {0.003};


        int gen = 500;
        GA ga1 = null;



//            for (int j=0; j < pm2.length; ++j){
//                int sum =0 ;
//                System.out.println("k,j: "+pm2[j]+"----"+pm0[0]);
//                for (int i = 0; i < 5; ++i) {
//                    //参数：种群大小，存活网络节点个数, 代数
//                    ga1 = new GA(popSize, graph.aliveNetVerticesNum, gen, graphProcess);
//
//                    if (graph.networkVertexnum < 250){
//                        System.out.println("hello0");
//                        ga1.pm0 = pm0[0];
//                        ga1.pm1 = 0.007;
//                        ga1.pm2 = pm2[j];
//
//                        ga1.pro_init_server = 0.5;
//
//                    }
//                    else if (graph.networkVertexnum < 500){
//                        System.out.println("hello1");
//                        ga1.pm0 = pm0[0];
//                        ga1.pm1 = 0.005;
//                        ga1.pm2 = pm2[j];
//
//                        ga1.pro_init_server = 0.6;
//                    }
//                    else {
//                        System.out.println("hello3");
//                        System.out.println(1.0 / graph.aliveNetVerticesNum);
//                        ga1.pm0 = pm0[0];
//                        ga1.pm1 = 0.005;
//                        ga1.pm2 = pm2[j];
//
//                        ga1.pro_init_server = 0.9;
//                    }
//
//
//                    ga1.startGA();
//                    list = ga1.getBestList();
//                    System.out.print(ga1.getBestCost()+" ");
//                    System.out.println(ga1.getBestId());
//
//
//                    PathCost pathCost = new PathCost(graph);
//
//                    graphProcess.addEdges();
//                    ga1.addSuperSource(ga1.bestServer);
//                    graph.serverIds = ga1.bestServer;
//
//                    int cost = pathCost.minPathCost(graph.table);
//                    System.out.println(cost);
//                    list = pathCost.getAllPathList();
//                    sum += cost;
//                }
//                sum = sum / 5;
//                System.out.println(sum);
//                System.out.println("======");
//            }







        for (int i = 0; i < 1; ++i) {
            //参数：种群大小，存活网络节点个数, 代数
            ga1 = new GA(popSize, graph.aliveNetVerticesNum, gen, graphProcess);

            if (graph.networkVertexnum < 250){
                ga1.pm0 = 0.003;
                ga1.pm1 = 0.007;
                ga1.pm2 = 0.01;

                ga1.pro_init_server = 0.5;

            }
            else if (graph.networkVertexnum < 500){
                ga1.pm0 = 0.003;
                ga1.pm1 = 0.005;
                ga1.pm2 = 0.01;

                ga1.pro_init_server = 0.6;
            }
            else {
                ga1.pm0 = 0.003;
                ga1.pm1 = 0.005;
                ga1.pm2 = 0.007;

                ga1.pro_init_server = 0.9;
            }


            ga1.startGA();
            list = ga1.getBestList();
            System.out.print(ga1.getBestCost()+" ");
            System.out.println(ga1.getBestId());


            PathCost pathCost = new PathCost(graph);

            graphProcess.addEdges();
            ga1.addSuperSource(ga1.bestServer);
            graph.serverIds = ga1.bestServer;

            System.out.println(pathCost.minPathCost(graph.table));
            list = pathCost.getAllPathList();
            System.out.println("======");
        }


        return StringsUtils.ListT0Strings(list);
    }

}
