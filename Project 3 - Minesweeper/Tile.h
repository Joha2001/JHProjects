#pragma once
#include "Random.h"
#include <vector>
#include <iostream>

class Tile
{
public:
	bool isFlagged, isMine, isRevealed;
	int nearbyMineAmount;
	std::vector<Tile*> adjTiles;
	Tile();
	Tile(int isAMine);
	void MineAmount();
};