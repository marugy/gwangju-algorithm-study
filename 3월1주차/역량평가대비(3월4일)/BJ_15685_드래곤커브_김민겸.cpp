#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

int map[101][101] = { 0 };
vector<pair<int, int>> dragon;

void rotateCW90(int row, int col) // 입력: 기준점
{
	int vsize = dragon.size();
	for (int i = vsize - 2; i >= 0; --i)
	{
		int dr = dragon[i].first - row;
		int dc = dragon[i].second - col;
		int tmp = dr;
		dr = dc;
		dc = -tmp;
		dragon.push_back({ row + dr, col + dc });
		map[row + dr][col + dc] = 1;
	}
}

// 드래곤 커브 1개 만들기
const int dir[4][2] = { 0,1, -1,0, 0,-1, 1,0 };
void create_dragon(int row, int col, int d, int g) // 입력: 시작 정보
{
	dragon.clear();
	dragon.push_back({ row,col });
	map[row][col] = 1;
	dragon.push_back({ row + dir[d][0],col + dir[d][1] });
	map[row + dir[d][0]][col + dir[d][1]] = 1;
	for (int i = 0; i < g; ++i)
	{
		auto endPos = dragon.back();
		rotateCW90(endPos.first, endPos.second);
	}
}

int check_rect()
{
	int cnt = 0;
	for (int i = 0; i < 100; ++i)
	{
		for (int j = 0; j < 100; ++j)
		{
			if (map[i][j] == 1 &&
				map[i + 1][j] == 1 &&
				map[i][j + 1] == 1 &&
				map[i + 1][j + 1] == 1)
			{
				++cnt;
			}
		}
	}
	return cnt;
}

int main()
{
	//freopen_s(new FILE*, "input.txt", "r", stdin);

	int N;
	cin >> N;
	for (int i = 0; i < N; ++i)
	{
		int row, col, d, g;
		cin >> col >> row >> d >> g;
		create_dragon(row, col, d, g);
	}
	cout << check_rect();

	return 0;
}

/*
3
3 3 0 1
4 2 1 3
4 2 2 1

답: 4
*/