#include <iostream>
#include <string>
#include <vector>
#include <math.h>
#include <chrono>
using namespace std;

int main()
{  
double N=1;
int minimum_increment=5; //minimum value for an increment of N in my method, for me 5 seemed to yield a decent performance without overshooting the value fro N in a later stage
double threshold=pow(10,-6); // here various thresholds higher than 10^-6 can be set and tested, for all of them both methods compute the same value for N
double offset_to_one=0.4; // the offset that is later onwards used to determine from which value onwards rounding will be applied
auto start = chrono::steady_clock::now(); // start time measurement for the improved method
while(true){
        double sum=0;
        for (double i=1;i<=N;i++){ //calculation of approximation formula happens here without the product term yet
            sum+=(1/(1+pow ((i-0.5)/N, 2)));
        }
        sum*=(4/N);
        double err=sum-M_PI; //calculate the difference between pi approximated with this formula and pi provided by math.h 
        double ratio=err/threshold;//calculate the ratio of the error and the threshold here to increase N depending on this size
        if (err<=threshold){
            break; //threshold has been reached
        }
        else // N is increased here depending on how far the algorithm is away from the threshold
        {
            if (ceil(log(ratio))>=minimum_increment){ //algorithm is in its starting state until the increments to N provided here fall below the minimal value for an increment set in minimum_increment
                N+=ceil(log(ratio)); //as the ratio is especially in the beginning is a very large number a logarithmic scale is used and this value rounded to a whole number
            }
            else if (round(ratio+offset_to_one)!=1){// at some point the ratio becomes smaller and the second phase begins here where the error is used instead to increment N, but only as long as the ratio is not very close to 1, here about 1.1 did yield good results
                N+=abs((int)round(log(err))); //now the error begins to asymptotically approach zero which on the other hand means that its logarithmic value yields larger and larger negative numbers
            }
            else{
                N++;
            }
        }
    }
    auto end = chrono::steady_clock::now();// stop time measurement for the improved method
    double duration_improved=chrono::duration_cast<chrono::nanoseconds>(end - start).count(); // calculate duration it took for the algorithm to run
    cout<<"The error is below "<<threshold<<" for N="<<N<<" in the improved method and ";
// here the old method is run once more to compare it with the improved version
N=1;
start = chrono::steady_clock::now(); // start time measurement for the old method
while(true){
        double sum=0;
        for (double i=1;i<=N;i++){
            sum+=(1/(1+pow ((i-0.5)/N, 2)));
        }
        sum*=(4/N);
        double err=sum-M_PI;
        if (err<=threshold){
            break;
        }
        else
        {
            N++;
        }
    }
end = chrono::steady_clock::now();// stop time measurement for the old method
double duration_old=chrono::duration_cast<chrono::nanoseconds>(end - start).count();// calculate duration it took for the algorithm to run
cout<<"for N="<<N<<" in the old method."<<endl;
cout << "The improved method is faster by " << duration_old-duration_improved<< " nanoseconds which is "<<round(duration_old/duration_improved)<<" times faster."<<endl;
}

	
