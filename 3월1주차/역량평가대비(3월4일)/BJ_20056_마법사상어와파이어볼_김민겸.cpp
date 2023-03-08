#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

struct BALL
{
	int row, col;
	int mass, speed, dir;
	int stamp;

	bool operator< (const BALL& right) const
	{
		if (stamp > right.stamp) return true;
		if (stamp < right.stamp) return false;
		if (row > right.row) return true;
		if (row < right.row) return false;
		if (col > right.col) return true;
		if (col < right.col) return false;
		return false;
	}
};

int N, M, K;
priority_queue<BALL> pq;
const int dir[8][2] = { -1,0, -1,1, 0,1, 1,1, 1,0, 1,-1, 0,-1, -1,-1 };

int adjust_pos(int p)
{
	if (p <= 0)
	{
		return ((N - p) / N) * N + p;
	}
	else
	{
		return (p - 1) % N + 1;
	}
}

void simulation()
{
	for (int iter = 0; iter < K; ++iter)
	{
		// 이동
		int pqsize = pq.size();
		for (int i = 0; i < pqsize; ++i)
		{
			BALL now = pq.top();
			pq.pop();

			int nr = now.row + now.speed * dir[now.dir][0];
			int nc = now.col + now.speed * dir[now.dir][1];
			nr = adjust_pos(nr);
			nc = adjust_pos(nc);

			now.row = nr;
			now.col = nc;
			now.stamp++;
			pq.push(now);
		}

		if (pq.empty()) break;

		// 처리 stamp 같은거
		int pqStamp = pq.top().stamp;;
		while (!pq.empty() && pqStamp == pq.top().stamp)
		{
			BALL now = pq.top();
			pq.pop();

			int totalMass = now.mass;
			int totalSpeed = now.speed;
			bool dirEvenFlag = 0, dirOddFlag = 0;
			if (now.dir % 2 == 0) dirEvenFlag = 1;
			else dirOddFlag = 1;

			int cnt = 1;
			while (!pq.empty() && pq.top().row == now.row && pq.top().col == now.col)
			{
				now = pq.top();
				pq.pop();
				totalMass += now.mass;
				totalSpeed += now.speed;
				if (now.dir % 2 == 0)
				{
					dirEvenFlag &= 1;
					dirOddFlag &= 0;
				}
				else
				{
					dirOddFlag &= 1;
					dirEvenFlag &= 0;
				}
				cnt++;
			}
			if (cnt > 1) // 겹치는 거 있으면
			{
				if (totalMass < 5) continue; // 사라짐

				if (dirEvenFlag || dirOddFlag) // 전부 짝수 또는 홀수
				{
					// 추가
					for (int i = 0; i <= 6; i += 2)
					{
						BALL newBall = now;
						newBall.dir = i;
						newBall.stamp++;
						newBall.speed = totalSpeed / cnt;
						newBall.mass = totalMass / 5;
						pq.push(newBall);
					}
				}
				else
				{
					// 추가
					for (int i = 1; i <= 7; i += 2)
					{
						BALL newBall = now;
						newBall.dir = i;
						newBall.stamp++;
						newBall.speed = totalSpeed / cnt;
						newBall.mass = totalMass / 5;
						pq.push(newBall);
					}
				}
			}
			else // 겹치는 거 없으면
			{
				now.stamp++;
				pq.push(now);
			}
		}
	}
}

int main()
{
	//freopen_s(new FILE*, "input.txt", "r", stdin);

	cin >> N >> M >> K;
	for (int i = 0; i < M; ++i)
	{
		int r, c, m, d, s;
		cin >> r >> c >> m >> s >> d;
		pq.push({ r,c,m,s,d,0 });
	}

	simulation();

	int sum = 0;
	while (!pq.empty())
	{
		auto now = pq.top();
		pq.pop();
		sum += now.mass;
	}

	cout << sum;


	return 0;
}

/*
3
3 3 0 1
4 2 1 3
4 2 2 1

답: 4
*/
