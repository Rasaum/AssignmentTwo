import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Task1 {

    static class Job {
        int jobID;
        int procTime;

        Job(int jobID, int procTime) {
            this.jobID = jobID;
            this.procTime = procTime;
        }
    }

    public static void main(String[] args) {
        String filePath = "C:/Users/Raissa Umwali/Downloads/task1-input.txt";

        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt(j -> j.procTime));

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                int jobID = Integer.parseInt(parts[0]);
                int procTime = Integer.parseInt(parts[1]);
                pq.offer(new Job(jobID, procTime));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        int currentTime =0;
        int totCompTime = 0;
        int numJobs = pq.size();
        StringBuilder execOrder = new StringBuilder();

        while (!pq.isEmpty()){
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
