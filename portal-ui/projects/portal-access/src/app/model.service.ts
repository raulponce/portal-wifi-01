import { Injectable } from '@angular/core';
import { InputQParams } from './model/InputQParams';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  public inputData : InputQParams = new InputQParams();

  constructor() { }
}
