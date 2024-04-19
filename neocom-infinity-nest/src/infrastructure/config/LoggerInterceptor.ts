import { Injectable, Logger, NestMiddleware } from "@nestjs/common";
import { Request, Response, NextFunction } from 'express';

@Injectable()
export class LoggerMiddleware implements NestMiddleware {
    private readonly logger = new Logger('LogInterception');
    use(req: Request, res: Response, next: NextFunction) {
        // Gets the request log
        this.logger.log(`req:`, {
            headers: req.headers,
            body: req.body,
            originalUrl: req.originalUrl,
        })
        // Ends middleware function execution, hence allowing to move on 
        if (next) {
            next()
        }
    }
}
