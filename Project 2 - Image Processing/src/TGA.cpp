#include "TGA.h"
TGA::TGA(string filename)
{
	ifstream inFile(filename, ios_base::binary);
	inFile.read(&header.idLength,1);
	inFile.read(&header.colorMapType, 1);
	inFile.read(&header.dataTypeCode, 1);
	inFile.read((char*)&header.colorMapOrigin,2);
	inFile.read((char*)&header.colorMapLength, 2);
	inFile.read((char*)&header.colorMapDepth, 1);
	inFile.read((char*)&header.xOrigin, 2);
	inFile.read((char*)&header.yOrigin, 2);
	inFile.read((char*)&header.width, 2);
	inFile.read((char*)&header.height, 2);
	inFile.read(&header.bitsPerPixel, 1);
	inFile.read(&header.imageDescriptor, 1);
	header.byteCount = header.width * header.height * 3;
	bytes = new unsigned char[header.byteCount];
	inFile.read((char*)bytes, header.byteCount * sizeof(bytes[0]));
	inFile.close();
}
void TGA::writeTGA(ofstream& file)
{
	file.write(&header.idLength, 1);
	file.write(&header.colorMapType, 1);
	file.write(&header.dataTypeCode, 1);
	file.write((char*)&header.colorMapOrigin, 2);
	file.write((char*)&header.colorMapLength, 2);
	file.write((char*)&header.colorMapDepth, 1);
	file.write((char*)&header.xOrigin, 2);
	file.write((char*)&header.yOrigin, 2);
	file.write((char*)&header.width, 2);
	file.write((char*)&header.height, 2);
	file.write(&header.bitsPerPixel, 1);
	file.write(&header.imageDescriptor, 1);
	file.write((char*)bytes, header.byteCount * sizeof(bytes[0]));
	file.close();
}
void TGA::Test(TGA example)
{
	bool test = true;
	for (int i = 0; i < header.byteCount; i++)
	{
		if (bytes[i] != example.bytes[i])
		{
			test = false;
		}
	}
	if (test)
	{
		cout << "Test successfully cleared!" << endl;
	}
}

void TGA::multiplyTGA(TGA bot)
{
	float pix_one = 0.0f;
	float pix_two = 0.0f;
	for (int i = 0; i < header.byteCount; i++)
	{
		pix_one = bytes[i]/255.00f;
		pix_two = bot.bytes[i]/255.00f;
		bytes[i] = (unsigned char)(pix_one * pix_two * 255.00f + .50f);
	}
}

void TGA::subtractTGA(TGA bot)
{
	float pix_one = 0.0f;
	float pix_two = 0.0f;
	float pix_com = 0.0f;
	for (int i = 0; i < header.byteCount; i++)
	{
		pix_one = bytes[i];
		pix_two = bot.bytes[i];
		pix_com = pix_two - pix_one;
		if (pix_com > 255.00f)
			pix_com = 255.00f;
		if (pix_com < 0.00f)
			pix_com = 0;
		bytes[i] = (unsigned char)pix_com;
	}
}

void TGA::screenTGA(TGA bot)
{
	float pix_one = 0.0f;
	float pix_two = 0.0f;
	for (int i = 0; i < header.byteCount; i++)
	{
		pix_one = 1.00f - (bytes[i] / 255.00f);
		pix_two = 1.00f - (bot.bytes[i] / 255.00f);
		bytes[i] = (unsigned char)((1 - pix_one * pix_two) * 255.00f + .50f);
	}
}
void TGA::overlayTGA(TGA bot)
{
	float pix_one = 0.0f;
	float pix_two = 0.0f;
	for (int i = 0; i < header.byteCount; i++)
	{
		
		pix_one = bytes[i] / 255.00f;
		pix_two = bot.bytes[i] / 255.00f;
		if (pix_two <= .50f)
			bytes[i] = (unsigned char)((2 *pix_one * pix_two) * 255.00f + .50f);
		else
			bytes[i] = (unsigned char)((1-(2 * (1-pix_one) * (1-pix_two))) * 255.00f + .50f);
	}
}
void TGA::channelTGA(bool option, int channel, float amount)
{
	float pix_one = 0.0f;
	float pix_com = 0.0f;
	for (int i = channel; i < header.byteCount; i += 3) 
	{
		if (option)
		{
			pix_one = bytes[i] / 255.00f;
			pix_com = amount * pix_one * 255 + .50f;
			if (pix_com > 255.00f)
				pix_com = 255.00f;
			if (pix_com < 0.00f)
				pix_com = 0;
			bytes[i] = (unsigned char)pix_com;
		}
		else
		{
			pix_one = bytes[i];
			pix_com = amount + pix_one;
			if (pix_com > 255.00f)
				pix_com = 255.00f;
			if (pix_com < 0.00f)
				pix_com = 0;
			bytes[i] = (unsigned char)pix_com;
		}
	}
}
void TGA::parseChannelTGA(int channel)
{
	unsigned char pix_one = bytes[channel];
	for (int i = 0; i < header.byteCount; i++)
	{
		if (i % 3 == 0)
		{
			pix_one = bytes[i + channel];
		}
		bytes[i] = pix_one;
	}
}
void TGA::combineTGA(TGA layer, int channel)
{
	for (int i = channel; i < header.byteCount; i += 3)
	{
		bytes[i] = (unsigned char)layer.bytes[i];
	}
}
void TGA::flipTGA()
{
	for (int i = 0; i < header.byteCount / 2; i += 3)
	{
		unsigned char tempx, tempy, tempz;
		tempx = bytes[i];
		tempy = bytes[i + 1];
		tempz = bytes[i + 2];
		bytes[i] = bytes[header.byteCount - 3 - i];
		bytes[i + 1] = bytes[header.byteCount - 2 - i];
		bytes[i + 2] = bytes[header.byteCount - 1 - i];
		bytes[header.byteCount - 3 - i] = tempx;
		bytes[header.byteCount - 2 - i] = tempy;
		bytes[header.byteCount - 1 - i] = tempz;
	}
}
void TGA::quadTGA(TGA a, TGA b, TGA c)
{
	int tempc = 0;
	int tempt = 0;
	header.width = (header.width + a.header.width + b.header.width + c.header.width) / 2;
	header.height = (header.height + b.header.height + b.header.height + c.header.height) / 2;
	header.byteCount = header.width * header.height *3;
	unsigned char* temp = bytes;
	bytes = new unsigned char[header.byteCount];
	//cout << header.height / 2;
	for (int i = 0; i < header.height / 2; i++) //Up to 511
	{
		//Bot left
		int counter = i * header.width * 3;
		for (int k = 0; k < (header.width / 2) * 3; k++)
		{
			bytes[counter + k] = c.bytes[tempc+k];
			bytes[counter + k + (header.width / 2) * 3] = b.bytes[tempc + k];
		}
		tempc += header.width/2 * 3;

	}
	for (int i = header.height / 2; i < header.height; i++) //Up to 511
	{
		//Top left
		int counter = i * header.width * 3;
		for (int k = 0; k < (header.width / 2) * 3; k++)
		{
			bytes[counter + k] = temp[tempt + k];
			bytes[counter + k + (header.width / 2) * 3] = a.bytes[tempt + k];
		}
		tempt += header.width / 2 * 3;

	}
	delete temp;
	temp = nullptr;
}