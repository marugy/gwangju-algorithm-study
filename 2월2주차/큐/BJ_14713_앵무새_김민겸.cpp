#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>

using namespace std;

// 아이디어:
// 각 문장 저장하고 dfs 돌리면서 짝 맞는지 확인
// 짝 맞는 즉시 dfs 탈출

struct SENTENCE
{
	string arr[100];
	int i = 0;
};

int N;
SENTENCE send[100];
string recv[10'000];
int len = 0;

bool dfs(int lvl)
{
	if (lvl == len)
	{
		return true;
	}

	for (int i = 0; i < N; ++i)
	{
		if (send[i].arr[send[i].i] != recv[lvl]) continue;
		send[i].i++;
		if(dfs(lvl + 1)) return true;
		send[i].i--;
	}

	return false;
}

int main()
{
	//freopen("input.txt", "r", stdin);

	cin >> N;

	int cnt = 0;
	for (int i = 0; i < N; ++i)
	{
		getchar();
		int j = 0;
		while (cin.peek() != '\n')
		{
			cin >> send[i].arr[j++];
			cnt++;
		}
	}
	getchar();
	while (cin.peek() != '\n')
	{
		cin >> recv[len++];
	}

	if (len == cnt && dfs(0)) cout << "Possible";
	else cout << "Impossible";


	return 0;
}