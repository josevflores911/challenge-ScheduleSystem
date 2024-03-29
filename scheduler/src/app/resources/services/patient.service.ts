import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Patient } from '../models/Patient';
import { environment } from 'src/environments/environments';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService {


  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient, private authService: AuthService) { }

  public getTry(): Observable<any> {

    const token: string = this.authService.getToken().token;
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token.replace(/"/g, ''),
      'Content-Type': 'application/json',
      'Accept': `application/json`
    });
    
    return this.http.get(`${this.apiServerUrl}/patient/hello`, { headers });
  }

  //getAll
  public getPatients(): Observable<Patient[]> {

    const token: any = this.authService.getToken().token;
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token.replace(/"/g, '')
    });


    return this.http.get<Patient[]>(`${this.apiServerUrl}/patient/getall`, { headers });
  }


  //getByDNI
  public getPatientByDNI(dni: string): Observable<Patient> {
    const token: string = this.authService.getToken().token;
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token.replace(/"/g, '')
    });
    return this.http.get<Patient>(`${this.apiServerUrl}/patient/getbydni/${dni}`,{headers})
  }

  //getById
  public getPatientById(id: number): Observable<Patient> {
    return this.http.get<Patient>(`${this.apiServerUrl}/patient/get/${id}`);
  }

  //post(save)
  public addPatient(patient: Patient | undefined): Observable<Patient> {
    //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const token: string = this.authService.getToken().token ;
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token.replace(/"/g, '')
    });
    return this.http.post<Patient>(`${this.apiServerUrl}/patient/add`, patient, { headers });
  }

  //put(update)
  public updatePatient(patient: Patient, id: number): Observable<Patient> {
    return this.http.put<Patient>(`${this.apiServerUrl}/patient/update/${id}`, patient);
  }

  //delete
  public deletePatient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/patient/delete/${id}`);
  }


}
