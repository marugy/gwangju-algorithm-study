#define _CRT_SECURE_NO_WARNINGS

#include <iostream>

using namespace std;

int N, S;
int arr[20];

int cnt = 0;
void bfs(int lvl, int nxt, int sum)
{
	if (sum == S) ++cnt;

	if (lvl == N) return;

	for (int i = nxt; i < N; ++i)
	{
		bfs(lvl + 1, i + 1, sum + arr[i]);
	}
}


int main()
{
	//freopen("input.txt", "r", stdin);
	
	cin >> N >> S;
	for (int i = 0; i < N; ++i) cin >> arr[i];
	bfs(0, 0, 0);
	if (S == 0) --cnt;
	cout << cnt;

	return 0;
}