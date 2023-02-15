#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <vector>

using namespace std;

vector<int> adj[1'000'001]; // 인접그래프
bool visited[1'000'001] = { 0 }; // 방문 기록

int cnt = 0;
bool foo(int now)
{
	visited[now] = 1;
	bool flag = true; // 자식이 모두 인플루언서인지?
	for (int next : adj[now])
	{
		if (visited[next]) continue;
		flag &= foo(next); // 인플루언서가 아닌 자식이 있으면 false 가 됨
	}
	visited[now] = 0;

	if (flag == false) // 인플루언서가 아닌 자식이 하나라도 있으면
	{
		++cnt; // 현재 노드를 인플루언서로 지정
		return true;
	}
	else
		return false;
}

int main()
{
	int n;
	cin >> n;

	for (int i = 0; i < n - 1; ++i)
	{
		int from, to;
		cin >> from >> to;
		adj[from].push_back(to);
		adj[to].push_back(from);
	}
	foo(1);
	cout << cnt;

	return 0;
}