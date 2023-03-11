#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

struct POS
{
	int row, col;
};

int N, M;
int A[51][51];
const int dir[9][2] = { 0,0,
	0,-1,
	-1,-1,
	-1,0,
	-1,1,
	0,1,
	1,1,
	1,0,
	1,-1
};

int get_index(int i)
{
	if (i > N) return (i - 1) % N + 1;
	if (i <= 0) return ((N - i) / N) * N + i;
	return i;
}

void go_move(vector<POS>& cloud, int d, int s)
{
	for (POS& p : cloud)
	{
		p.row = get_index(p.row + dir[d][0]*s);
		p.col = get_index(p.col + dir[d][1]*s);
	}
}

void simulation(vector<POS>& cloud, int d, int s)
{
	// 이동
	go_move(cloud, d, s);

	// 비내림
	bool visited[51][51] = { 0 };
	for (const POS& p : cloud)
	{
		A[p.row][p.col]++;
		visited[p.row][p.col] = 1;
	}

	// 물 복사 버그 시전
	for (const POS& p : cloud)
	{
		for (int i = 2; i <= 8; i += 2)
		{
			int nr = p.row + dir[i][0];
			int nc = p.col + dir[i][1];
			if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
			if (A[nr][nc] > 0)
			{
				A[p.row][p.col]++;
			}
		}
	}

	// 구름 생성
	cloud.clear();
	for (int i = 1; i <= N; ++i)
	{
		for (int j = 1; j <= N; ++j)
		{
			if (A[i][j] >= 2 && !visited[i][j])
			{
				A[i][j] -= 2;
				cloud.push_back({ i,j });
			}
		}
	}
}

int main()
{
	cin >> N >> M;
	for (int i = 1; i <= N; ++i)
	{
		for (int j = 1; j <= N; ++j)
			cin >> A[i][j];
	}

	vector<POS> cloud;
	cloud.push_back({ N,1 });
	cloud.push_back({ N,2 });
	cloud.push_back({ N - 1,1 });
	cloud.push_back({ N - 1,2 });

	for (int i = 0; i < M; ++i)
	{
		int d, s;
		cin >> d >> s;
		simulation(cloud, d, s);
	}

	int sum = 0;
	for (int i = 1; i <= N; ++i)
	{
		for (int j = 1; j <= N; ++j)
		{
			sum += A[i][j];
		}
	}
	
	cout << sum;

	return 0;
}