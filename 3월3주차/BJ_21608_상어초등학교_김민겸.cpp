#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

struct POS
{
	int row, col;
};


int N;
int satisfy[5] = { 0,1,10,100,1000 };
POS seated[20 * 20 + 1];
int visited[21][21];
int like[20 * 20 + 1][4];

const int dir[4][2] = { 1,0,0,1,-1,0,0,-1 };

int empty_count(int row, int col)
{
	int cnt = 0;
	for (int i = 0; i < 4; ++i)
	{
		int nr = row + dir[i][0];
		int nc = col + dir[i][1];
		if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
		if (visited[nr][nc]) continue;
		cnt++;
	}
	return cnt;
}

int count_like(int student)
{
	int cnt = 0;
	int row = seated[student].row;
	int col = seated[student].col;
	for (int i = 0; i < 4; ++i)
	{
		int r = seated[like[student][i]].row;
		int c = seated[like[student][i]].col;

		int dist = abs(row - r) + abs(col - c);
		if (dist == 1) cnt++;
	}
	return cnt;
}

void set_seat(vector<POS>& v, int student, int like[4])
{
	int dat[21][21] = { 0 };

	for (int i = 0; i < 4; ++i)
	{
		POS tmp = seated[like[i]];
		if (tmp.row == 0 && tmp.col == 0) continue;
		for (int i = 0; i < 4; ++i)
		{
			int nr = tmp.row + dir[i][0];
			int nc = tmp.col + dir[i][1];
			if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
			if (visited[nr][nc]) continue;
			dat[nr][nc]++;
		}
	}

	nth_element(v.begin(), v.end() - 1, v.end(), [&](const POS& a, const POS& b)
		{
			// 1
			if (dat[a.row][a.col] < dat[b.row][b.col]) return true;
			if (dat[a.row][a.col] > dat[b.row][b.col]) return false;
			// 2
			int aCnt = empty_count(a.row, a.col);
			int bCnt = empty_count(b.row, b.col);
			if (aCnt < bCnt) return true;
			if (aCnt > bCnt) return false;
			// 3
			if (a.row > b.row) return true;
			if (a.row < b.row) return false;
			if (a.col > b.col) return true;
			if (a.col < b.col) return false;

			return false;
		});

	POS tmp = v.back();
	v.pop_back();
	seated[student] = tmp;
	visited[tmp.row][tmp.col] = student;
}

int main()
{
	cin >> N;

	vector<POS> v;
	for (int i = 1; i <= N; ++i)
	{
		for (int j = 1; j <= N; ++j)
		{
			v.push_back({ i,j });
		}
	}

	for (int i = 0; i < N * N; ++i)
	{
		int student;
		cin >> student;
		for (int j = 0; j < 4; ++j) cin >> like[student][j];
		set_seat(v, student, like[student]);
	}

	int sum = 0;
	for (int i = 1; i <= N; ++i)
	{
		for (int j = 1; j <= N; ++j)
		{
			sum += satisfy[count_like(visited[i][j])];
		}
	}

	cout << sum;


	return 0;
}