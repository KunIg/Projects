#include <iostream>
#include <string>
#include <vector>
#include <math.h>
#include <chrono>
using namespace std;

int main()
{  
auto start = chrono::steady_clock::now();
bool not_found=true;
double N=1;
while(not_found){
        double sum=0;
        double divisor=0;
        for (double i=1;i<=N;i++){
            divisor=(i-0.5)/N;
            sum+=(1/(1+pow (divisor, 2)));
        }
        sum*=(4/N);
        double err=sum-M_PI;
        if (err<=0.000001){
            not_found=false;
        }
        else
        {
        N++;    
        }
    }
    auto end = chrono::steady_clock::now();
    cout<<"The error is below 10^-6 for N="<<N<<endl;
    cout << "Elapsed time in nanoseconds is " << chrono::duration_cast<chrono::nanoseconds>(end - start).count()
	<< " ns" << endl;
}

	
