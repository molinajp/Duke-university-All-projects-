import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPts
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int totalP= 0;
        for  (Point p : s.getPoints()) {
            int currP= 1;           
            totalP= currP + totalP;
        }
        return totalP;
    }

    public double getAverageLength(Shape s) {
        int div= getNumPoints (s);
        double length= getPerimeter (s);
        double avg= length/div;
        return avg;
    }

    public double getLargestSide(Shape s) {
        double longest = 0.0;
        Point prevPoint = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double currDist = prevPoint.distance (currPt);
            if (longest<currDist){
                longest=currDist;            
            }
        }
        return longest;
    }

    public double getLargestX(Shape s) {
        double largestX = Double.NEGATIVE_INFINITY;
        for (Point p : s.getPoints()){
            int currentX = p.getX();
            if (currentX>largestX){
                largestX=currentX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double longestPerimeter= 0.0;
        DirectoryResource dr= new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr= new FileResource(f);
            Shape s = new Shape (fr);
            double currPerimeter= getPerimeter (s);
            if (longestPerimeter<currPerimeter){
                longestPerimeter= currPerimeter;
            }
        }
        return longestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
         double longestPerimeter= 0.0;
         String filename= null;
        DirectoryResource dr= new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr= new FileResource(f);
            Shape s = new Shape (fr);
            double currPerimeter= getPerimeter (s);
            if (longestPerimeter<currPerimeter){
                longestPerimeter= currPerimeter;
                filename= f.getName();
            }
        }
        return filename;        
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println ("Number of points = " + getNumPoints (s));
        System.out.println ("Average length = " + getAverageLength (s));
        System.out.println ("Largest side= " + getLargestSide (s));
        System.out.println ("Largest X = " + getLargestX (s));
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double length = getLargestPerimeterMultipleFiles();
        System.out.println (length);
    }

    public void testFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        String file = getFileWithLargestPerimeter();
        System.out.println (file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
