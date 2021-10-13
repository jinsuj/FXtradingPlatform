import { LotSize } from './lot-size';
import { LotSizesEnum } from './Lot-sizes-enum.model';


export class Trade {

    user_id: number;
    currencyfrom_id: number;
    currencyto_id: number;
    profit: number = 0;
    trade_date: any;
    date: Date;

    lotSize: LotSizesEnum = LotSizesEnum.NANO;

  }
