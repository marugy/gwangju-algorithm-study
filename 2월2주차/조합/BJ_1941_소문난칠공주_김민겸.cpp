#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>
#include <queue>

using namespace std;

// ���̵��:
// 2���� �迭�� 1�����迭�� ���� ��� ������ ���� (foo�Լ�)
// BFS ���� ���� ������ ����Ǿ��ִ��� Ȯ���ϰ� �Ҵ��� �ο� 4�� �̻����� Ȯ�� (isValid)
// ������ �����̸� cnt ����

// �� ����� ���̵� ���� �� ������ ������ �ȳ�.

struct POS
{
	int row, col;
};

char map[5][5];
bool mark[5][5] = { 0 };
const int dir[4][2] = { 1,0, 0,1, -1,0, 0,-1 };

bool isValid(POS srt)
{
	queue<POS> q;
	bool visited[5][5] = { 0 };
	q.push(srt);
	visited[srt.row][srt.col] = 1;

	int cntSom = 0;
	int total = 0;
	while (!q.empty())
	{
		POS now = q.front();
		q.pop();
		total++;

		if (map[now.row][now.col] == 'S') cntSom++;

		for (int i = 0; i < 4; ++i)
		{
			int nr = now.row + dir[i][0];
			int nc = now.col + dir[i][1];
			if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
			if (visited[nr][nc] || !mark[nr][nc]) continue;
			q.push({ nr,nc });
			visited[nr][nc] = 1;
		}
	}
	if (total < 7 || cntSom < 4) return false;
	return true;
}

int cnt = 0;
void foo(int lvl, int nxt)
{
	if (lvl == 7)
	{
		int row = (nxt - 1) / 5;
		int col = (nxt - 1) % 5;
		if (isValid({ row,col })) ++cnt;
		return;
	}

	for (int i = nxt; i < 25; ++i)
	{
		int row = i / 5;
		int col = i % 5;

		mark[row][col] = 1;
		foo(lvl + 1, i + 1);
		mark[row][col] = 0;
	}

}

int main()
{
	//freopen("input.txt", "r", stdin);

	for (int i = 0; i < 5; ++i)
	{
		for (int j = 0; j < 5; ++j)
			cin >> map[i][j];
	}
	foo(0, 0);
	cout << cnt;


	return 0;
}