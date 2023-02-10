#include <iostream>

using namespace std;

int N;
int nums[12];
int ops[4];
int minVal, maxVal;

int go_operation(int a, int b, int i)
{
	// ��Ģ ����
	switch (i)
	{
	case 0: return a + b;
	case 1: return a - b;
	case 2: return a * b;
	case 3: return a / b;
	}
}

void bfs(int lvl, int val)
{
	if (lvl == N)
	{
		// �ִ� �ּ� ����
		if (minVal > val) minVal = val;
		if (maxVal < val) maxVal = val;
		return;
	}

	for (int i = 0; i < 4; ++i)
	{
		// ������ �پ��� ��ŵ
		if (ops[i] == 0) continue;

		// ����
		ops[i]--;
		bfs(lvl + 1, go_operation(val, nums[lvl], i));
		ops[i]++;
	}
}


int main()
{
	int T;
	cin >> T;

	for (int t = 1; t <= T; ++t)
	{
		cin >> N;
		for (int i = 0; i < 4; ++i) cin >> ops[i];
		for (int i = 0; i < N; ++i) cin >> nums[i];

		minVal = 2e9;
		maxVal = -2e9;
		bfs(1,nums[0]);
		cout << "#" << t << " " << maxVal - minVal << endl;
	}

	return 0;
}