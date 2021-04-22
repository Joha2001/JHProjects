#include "Tile.h"
#include <fstream>
#include <string>
#include <iostream>

class Board 
{
public:
	int mineCount;
	std::vector<Tile> tiles;
	Board();
	void Reset();
	void Load(const char* filename);
	void SetAdjTiles();
};