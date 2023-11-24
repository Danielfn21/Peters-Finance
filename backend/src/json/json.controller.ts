import { Controller, Get, Res } from '@nestjs/common';
import { Response } from 'express';
import * as fs from 'fs';

@Controller('Users')
export class JsonController {
    @Get()
    async serveJson(@Res() res: Response): Promise<void> {
      try {
        const jsonData = fs.readFileSync('data.json', 'utf8');
        const parsedData = JSON.parse(jsonData);
        res.json(parsedData);
      } catch (error) {
        console.error('Error reading JSON file:', error);
        res.status(500).json({ error: 'Internal Server Error' });
      }
    }
}
