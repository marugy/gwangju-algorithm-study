#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<cstring>

using namespace std;

struct POS
{
	int row, col, dir;
};

POS fish[17];
int fishId[4][4];
bool die[17];
const int dir[9][2] =
{
	0,0,
	-1,0,
	-1,-1,
	0,-1,
	1,-1,
	1,0,
	1,1,
	0,1,
	-1,1
};

void move_fish(POS shark)
{
	for (int i = 1; i <= 16; ++i)
	{
		if (die[i]) continue;
		int d = fish[i].dir;
		int r = fish[i].row;
		int c = fish[i].col;
		for (int j = 0; j < 8; ++j)
		{
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 ||
				(nr == shark.row && nc == shark.col))
			{
				d = (d % 8) + 1;
				continue;
			}
			fish[i].dir = d;
			swap(fish[i].row, fish[fishId[nr][nc]].row);
			swap(fish[i].col, fish[fishId[nr][nc]].col);
			swap(fishId[r][c], fishId[nr][nc]);
			break;
		}
	}
}

int maxSum;
void shark_move(int r, int c, int sum)
{
	sum += fishId[r][c];
	die[fishId[r][c]] = 1;
	if (maxSum < sum) 
		maxSum = sum;

	int d = fish[fishId[r][c]].dir;
	int nr = r;
	int nc = c;

	int fishId0[4][4];
	POS fish0[17];
	memcpy(fishId0, fishId, sizeof(fishId0));
	memcpy(fish0, fish, sizeof(fish0));

	move_fish({ r,c });

	while (1)
	{
		nr += dir[d][0];
		nc += dir[d][1];
		if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) break;
		if (!die[fishId[nr][nc]])
		{
			shark_move(nr, nc, sum);
		}
	}

	die[fishId[r][c]] = 0;
	memcpy(fishId, fishId0, sizeof(fishId));
	memcpy(fish, fish0, sizeof(fish));
}

int main()
{
	//freopen("sample_input.txt", "r", stdin);

	for (int i = 0; i < 4; ++i)
	{
		for (int j = 0; j < 4; ++j)
		{ 
			int id, d;
			cin >> id >> d;
			fishId[i][j] = id;
			fish[id].row = i;
			fish[id].col = j;
			fish[id].dir = d;
		}
	}
	
	shark_move(0,0,0);
	cout << maxSum;


	return 0;
}