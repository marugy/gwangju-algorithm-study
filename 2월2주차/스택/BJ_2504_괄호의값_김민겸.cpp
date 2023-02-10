#define _CRT_SECURE_NO_WARNINGS

#include <iostream>

using namespace std;

char arr[100];
int i = 0;

int foo(char now)
{
	int sum = 0;
	while (1)
	{
		char op = arr[i++];

		if (op == 0) return 0;

		if (now == '(' && op == ')')
		{
			if (sum == 0) return 2;
			else return 2 * sum;
		}
		if (now == '[' && op == ']')
		{
			if (sum == 0) return 3;
			else return 3 * sum;
		}

		int tmp = foo(op);
		if (tmp == 0) return 0;
		else sum += tmp;
	}

	
}

int main()
{
	//freopen("input.txt", "r", stdin);
	
	cin >> arr;
	int sum = 0;
	for (i = 0; arr[i] != 0;)
	{
		int tmp = foo(arr[i++]);
		if (tmp == 0)
		{
			cout << 0;
			return 0;
		}
		sum += tmp;
	}
	cout << sum;


	return 0;
}