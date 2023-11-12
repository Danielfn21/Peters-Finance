import { Module } from '@nestjs/common';
import { JsonController } from './json/json.controller';

@Module({
  imports: [],
  controllers: [JsonController]
})
export class AppModule {}
