import java.io.*;
import java.util.*;

public class Task3 {

    static class Job {
        int jobID;
        int procTime;
        int arriTime;

        Job(int jobID, int procTime, int arriTime) {
            this.jobID = jobID;
            this.procTime = procTime;
            this.arriTime = arriTime;
        }
    }

    public static void main(String[] args) {
        String filePath = "C:/Users/Raissa Umwali/Downloads/task3-input.txt";

        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt((Job j) -> j.procTime));

        Queue<Job> jobQueue = new LinkedList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                int jobID = Integer.parseInt(parts[0]);
                int procTime = Integer.parseInt(parts[1]);
                int arriTime = Integer.parseInt(parts[2]);

                pq.offer(new Job(jobID, procTime, arriTime));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        int currentTime =0;
        int totCompTime = 0;
        int numJobs = 100;
        StringBuilder execOrder = new StringBuilder();

        while (!pq.isEmpty() || !jobQueue.isEmpty()){

            while (!jobQueue.isEmpty() && jobQueue.peek().arriTime <= currentTime){
                pq.offer(jobQueue.poll());
            }

            if (pq.isEmpty()){
                currentTime = jobQueue.peek().arriTime;
                continue;
            }


            Job job = pq.poll();

            currentTime += job.procTime;
            totCompTime += currentTime;
            execOrder.append(job.jobID).append(" ");
        }


        double avgCompTime = (double) totCompTime / numJobs;

        //print out the execution order and the completion time
        System.out.println("Execution order: " + "[" + execOrder.toString().trim() + "]");
        System.out.println("Average completion time: " + avgCompTime);
    }

}
