package frc.robot;
import java.lang.Math;
//I fixed the syntax errors, declared variables, switched to doubles, and imported math library properly

class CameraMath //extends Component
{

    //the amount of pixels in 1 inch
    double inch;
    //the amount of pixels dists can be off
    double error;

    //constructor - one arg - height of camera off the ground
    public CameraMath(double in_height, int in_error) {
        //this sets inch in to the correct number
        inch = 2000 / (in_height * (5.039 / 6));

        //this sets the error to the right error
        error = in_error;
    }

    //this is the main math function
    public double math(int[] points){
        //find the highest point
        int highest_index = 1;
        for(int i = 1; i < 8; i += 1){
            if(points[i] > points[highest_index]){
                highest_index = i;
            }
        }
        highest_index--;

        //get the distances from that point
        double xa = (points[(highest_index - 2) % 4] - points[(highest_index)]);
        double ya = (points[(highest_index - 1) % 4] - points[(highest_index + 1)]);
        double dist_a = Math.sqrt(xa * xa + ya * ya);
        double xb = (points[(highest_index + 2) % 4] - points[(highest_index)]);
        double yb = (points[(highest_index + 3) % 4] - points[(highest_index) + 1]);
        double dist_b = Math.sqrt(xb * xb + yb * yb);

        //figure out which direction it will go
        if(dist_a == dist_b){
            return 0;
        } else if(2 * inch - error < dist_a && dist_a < 2 * inch + error){
            return Math.atan(ya / xa);
        } else if(2 * inch - error < dist_b && dist_b < 2 * inch + error){
            return Math.atan(yb / xb);
        } else {
            //throw an exception if the input is bad
            throw new IllegalArgumentException("Error in CameraMath Java.java: You wrote the code above this error thrower badly. Try fixing it until the code stops erring.");
        }

    }

    /*
    //this is for tick updates
    @Override
    public Update(){

    }
*/
}
