#define _CRT_SECURE_NO_WARNINGS

#include <iostream>

using namespace std;

int D, W, K;
int map[13][20];
int path[13] = { 0 }; // X:0, A:1, B:2

bool check()
{
	for (int i = 0; i < W; ++i)
	{
		int cnt = 1;
		int prev = map[0][i];
		if (path[0] != 0) prev = path[0];

		for (int j = 1; j < D; ++j)
		{
			int cur = map[j][i];
			if (path[j] != 0) cur = path[j];

			if (cur == prev) ++cnt;
			else cnt = 1;
			if (cnt == K) break;

			prev = cur;
		}
		if (cnt < K) return false;
	}
	return true;
}

int minCnt;
void foo(int lvl, int cnt)
{
	if (lvl == D)
	{
		if (check() && cnt < minCnt)
			minCnt = cnt;
		return;
	}

	if (cnt > minCnt) return;

	for (int i = 0; i < 3; ++i)
	{
		path[lvl] = i;
		foo(lvl + 1, cnt + (i > 0));
	}
}

int main()
{
	//freopen("input.txt", "r", stdin);

	int T;
	cin >> T;
	for (int t = 1; t <= T; ++t)
	{
		cin >> D >> W >> K;
		for (int i = 0; i < D; ++i)
		{
			for (int j = 0; j < W; ++j)
			{
				cin >> map[i][j];
				map[i][j]++;
			}
		}

		minCnt = K;
		foo(0, 0);
		cout << "#" << t << " " << minCnt << endl;
	}



	return 0;
}