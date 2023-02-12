#include <iostream>

using namespace std;

struct INFO
{
    int weight;
    int at = 0;
};

int main()
{
    int n, w, L;
    cin >> n >> w >> L;

    INFO arr[1001];
    for (int i = 0; i < n; ++i)
    {
        cin >> arr[i].weight;
    }

    int t = 0, sum = 0;
    int lp = 0, rp = 0;
    while (lp < n)
    {
        bool flag = true;

        // 나갈 트럭
        if (w == t - arr[lp].at)
        {
            sum -= arr[lp].weight;
            lp++;
            flag = false;
        }

        // 들어올 트럭
        if (rp < n && sum + arr[rp].weight <= L)
        {
            arr[rp].at = t;
            sum += arr[rp].weight;
            rp++;
            flag = false;
        }

        if(flag)
        {
            // 타임 워프
            t += w - (t - arr[lp].at);
        }
        else 
        {
            t++;
        }
    }
    cout << t;


    return 0;
}

