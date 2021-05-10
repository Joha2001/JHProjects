#include <iostream>
#include <iomanip>
#include <vector>
#include <map>
using namespace std;
class AdjacencyList {
private:
	map<string, vector<string>> adjList; //A map with string keys that hold string vectors
public:
	void Add(string from, string to) {
		if (adjList.size() == 0) { // If the adjList map is empty, create the first key and create a vector with the to address.
			vector<string> toPages;
			toPages.push_back(to);
			adjList.emplace(from, toPages);
		}
		else { // Otherwise, the function will iterate through the adjList map.
			bool keyExist = false;
			for (auto iter = adjList.begin(); iter != adjList.end(); iter++) {
				string keyVal = iter->first;
				if (keyVal.compare(from) == 0) { //If the key value already exists, the current vector at that key will hold the additional to address.
					keyExist = true;
					iter->second.push_back(to);
				}
			}
			if (!keyExist) { // If the key value does not exist, the function will create another key and vector with the specific to address.
				vector<string> toPages;
				toPages.push_back(to);
				adjList.emplace(from, toPages);
			}
		}
		bool toChk = false;
		for (auto iterX = adjList.begin(); iterX != adjList.end(); iterX++) { // Additionally, the function will iterate to make sure that the to address is also a key.
			if (iterX->first.compare(to) == 0) {
				toChk = true;
			}
		}
		if (!toChk) { // If a key for the to address does not exist, one will be created with an empty vector.
			vector<string> empty;
			adjList.emplace(to, empty);
		}
	}
	void rankCalculation(int iterations) {
		map<string, float> rankCalc; // This map will hold all of the calculations for ranks, and be printed out in alphabetical order.
		for (auto iter = adjList.begin(); iter != adjList.end(); iter++) { // The loop sets each address to the initial rank values, or 1 / |V|.
			rankCalc.emplace(iter->first, 1.000000f / ((float)adjList.size()));
		}
		map<string, float> tempCalc; // A temporary map to hold calculations (for power iteration)
		for (int i = 1; i < iterations; i++) { // Runs for required power iterations 
			for (auto iter = adjList.begin(); iter != adjList.end(); iter++) {
				float temp = 0.000000f;
				for (auto iterX = adjList.begin(); iterX != adjList.end(); iterX++) {
					for (int j = 0; j < iterX->second.size(); j++) {
						if (iterX->second[j].compare(iter->first) == 0) { // If the current from address holds the to address that is being searched for, the index is multiplied by the same index of the rank values.
							temp += rankCalc[iterX->first] * (1.000000f / ((float)adjList[iterX->first].size()));
						}
					}
				}
				tempCalc.emplace(iter->first, temp);
			}
			rankCalc = tempCalc; // Replaces the previous rank iterations with the newly calculated ones.
			tempCalc.clear(); // Clears the temporary calculations in case of another iteration.
		}
		for (auto iter = rankCalc.begin(); iter != rankCalc.end(); iter++) { // Print the adjLists in alphabetical order.
			cout << fixed << setprecision(2); // Sets the precision to two decimal points
			cout << iter->first << " " << iter->second << endl;	
		}
	}
};
int main() {
	AdjacencyList pageRank;
	int no_of_lines, power_iterations; // Utilizing the input method from the project handout
	string from, to;
	cin >> no_of_lines;
	cin >> power_iterations;
	for (int i = 0; i < no_of_lines; i++) {
		cin >> from;
		cin >> to;
		pageRank.Add(from, to);
	} // This marks the end of creating the Adjacency List
	pageRank.rankCalculation(power_iterations); 
	/*for (auto iter = adjList.begin(); iter != adjList.end(); iter++) { Shows what each list looks like	
			cout << iter->first;
			for (int i = 0; i < iter->second.size(); i++)
			{
				cout << " -> " << iter->second[i] ;
			}
			cout << endl;
	}*/
}