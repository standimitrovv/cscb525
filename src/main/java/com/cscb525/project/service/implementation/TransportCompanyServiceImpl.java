package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.*;
import com.cscb525.project.model.client.Client;
import com.cscb525.project.model.employee.Employee;
import com.cscb525.project.model.revenue.TransportCompanyRevenue;
import com.cscb525.project.model.transportCompany.TransportCompany;
import com.cscb525.project.model.vehicle.Vehicle;
import com.cscb525.project.repository.*;
import com.cscb525.project.service.TransportCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

    private final TransportCompanyRepository transportCompanyRepository;

    private final ClientRepository clientRepository;

    private final TransportCompanyRevenueRepository transportCompanyRevenueRepository;

    private final VehicleRepository vehicleRepository;

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TransportCompanyServiceImpl(
            TransportCompanyRepository transportCompanyRepository,
            ClientRepository clientRepository,
            TransportCompanyRevenueRepository transportCompanyRevenueRepository,
            VehicleRepository vehicleRepository,
            EmployeeRepository employeeRepository
    ){
        this.transportCompanyRepository = transportCompanyRepository;
        this.clientRepository = clientRepository;
        this.transportCompanyRevenueRepository = transportCompanyRevenueRepository;
        this.vehicleRepository = vehicleRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<TransportCompanyDtoResponse> getAllTransportCompanies(){
        List<TransportCompany> transportCompanies = this.transportCompanyRepository.findAll();

        return transportCompanies
                .stream()
                .map(c -> modelMapper.map(c, TransportCompanyDtoResponse.class))
                .collect(Collectors.toList());

    }

    public TransportCompanyDtoResponse getTransportCompany(int companyId){
        TransportCompany transportCompany = this.findTransportCompanyByIdOrThrow(companyId);

        return modelMapper.map(transportCompany, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse addTransportCompany(TransportCompanyDto transportCompany) {
        TransportCompany transportCompanyToAdd = modelMapper.map(transportCompany, TransportCompany.class);

        transportCompanyToAdd.setName(transportCompany.getName());

        return modelMapper.map(this.transportCompanyRepository.save(transportCompanyToAdd), TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse updateTransportCompany(TransportCompanyDto transportCompanyDto, int companyId){
        TransportCompany transportCompany = this.findTransportCompanyByIdOrThrow(companyId);

        transportCompany.setName(transportCompanyDto.getName());

        return modelMapper.map(this.transportCompanyRepository.save(transportCompany), TransportCompanyDtoResponse.class);
    }

    public void deleteTransportCompany(int companyId) {
        findTransportCompanyByIdOrThrow(companyId);

        this.transportCompanyRepository.deleteById(companyId);
    }

    // #region COMPANY CLIENT
    public TransportCompanyDtoResponse addClient(int companyId, int clientId){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        Client client = findClientByIdOrThrow(clientId);

        if(transportCompany.getClients().contains(client)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        transportCompany.getClients().add(client);
        this.transportCompanyRepository.save(transportCompany);

        return this.modelMapper.map(transportCompany, TransportCompanyDtoResponse.class);
    }

    public void deleteCompanyClient(int companyId, int clientId) {
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        Client client = findClientByIdOrThrow(clientId);

        if(!transportCompany.getClients().contains(client)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        transportCompany.getClients().remove(client);
        this.transportCompanyRepository.save(transportCompany);

        this.transportCompanyRepository.save(transportCompany);
    };

    public TransportCompanyDtoResponse updateCompanyClient(int companyId, int clientId, ClientDto clientDto){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        Client c = findClientByIdOrThrow(clientId);

        if(!transportCompany.getClients().contains(c)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        c.setName(clientDto.getName());
        this.clientRepository.save(c);

        return this.modelMapper.map(
                this.transportCompanyRepository.save(transportCompany),
                TransportCompanyDtoResponse.class
        );
    }
    // #endregion COMPANY CLIENT

    // #region COMPANY REVENUE
    public TransportCompanyDtoResponse addCompanyRevenue(int companyId, TransportCompanyRevenueDto revenueDto){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        TransportCompanyRevenue transportCompanyRevenue = new TransportCompanyRevenue();

        transportCompanyRevenue.setTransportCompany(transportCompany);
        transportCompanyRevenue.setForMonth(revenueDto.getForMonth());
        transportCompanyRevenue.setRevenue(revenueDto.getRevenue());

        this.transportCompanyRepository.save(transportCompany);
        this.transportCompanyRevenueRepository.save(transportCompanyRevenue);

        return this.modelMapper.map(transportCompany, TransportCompanyDtoResponse.class);
    }

    public List<TransportCompanyRevenueDtoResponse> getAllCompanyRevenues(int companyId){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        return transportCompany
                .getRevenues()
                .stream()
                .map(revenue -> modelMapper.map(revenue, TransportCompanyRevenueDtoResponse.class))
                .collect(Collectors.toList());
    }

    public List<TransportCompanyRevenueDtoResponse> updateCompanyRevenue(int companyId, int revenueId, TransportCompanyRevenueDto revenueDto){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        TransportCompanyRevenue tcr = findTransportCompanyRevenueByIdOrThrow(revenueId);

        if(!transportCompany.getRevenues().contains(tcr)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        tcr.setRevenue(revenueDto.getRevenue());
        tcr.setForMonth(revenueDto.getForMonth());

        this.transportCompanyRepository.save(transportCompany);

        return transportCompany
                .getRevenues()
                .stream()
                .map(r -> modelMapper.map(r, TransportCompanyRevenueDtoResponse.class))
                .collect(Collectors.toList());
    }
    // #endregion COMPANY REVENUE

    // #region COMPANY VEHICLE
    public TransportCompanyDtoResponse addCompanyVehicle(int companyId, int vehicleId){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Vehicle vehicle = findVehicleByIdOrThrow(vehicleId);

        vehicle.setCompany(company);

        this.vehicleRepository.save(vehicle);

        return this.modelMapper.map(company, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse updateCompanyVehicle(int companyId, int vehicleId, VehicleDto vehicleDto){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Vehicle vehicle = findVehicleByIdOrThrow(vehicleId);

        if(!company.getVehicles().contains(vehicle)){
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);
        }

        vehicle.setVehicleType(vehicleDto.getVehicleType());
        this.vehicleRepository.save(vehicle);

        return this.modelMapper.map(company, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse deleteCompanyVehicle(int companyId, int vehicleId){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Vehicle v = findVehicleByIdOrThrow(vehicleId);

        if(!company.getVehicles().contains(v)){
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);
        }

        this.vehicleRepository.deleteCompany(companyId);

        return this.modelMapper.map(company, TransportCompanyDtoResponse.class);
    }
    // #endregion COMPANY VEHICLE

    // #region COMPANY EMPLOYEE
    public List<EmployeeDtoResponse> getAllCompanyEmployees(int companyId){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        return company
                .getEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeDtoResponse.class))
                .collect(Collectors.toList());
    }

    public TransportCompanyDtoResponse addCompanyEmployee(int companyId, int employeeId){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Employee employee = findEmployeeByIdOrThrow(employeeId);

        boolean employeeExists = company.getEmployees().contains(employee);

        if(employeeExists){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        company.getEmployees().add(employee);
        employee.setCompany(company);

        this.employeeRepository.save(employee);
        this.transportCompanyRepository.save(company);

        return this.modelMapper.map(company, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse updateCompanyEmployee(int companyId, int employeeId, EmployeeDto employeeDto){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Employee e = findEmployeeByIdOrThrow(employeeId);

        if(!company.getEmployees().contains(e)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        e.setSalary(employeeDto.getSalary());
        e.setDrivingQualification(employeeDto.getDrivingQualification());
        this.employeeRepository.save(e);

        return this.modelMapper.map(company, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse deleteCompanyEmployee(int companyId, int employeeId){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Employee e = findEmployeeByIdOrThrow(employeeId);

        if(!company.getEmployees().contains(e)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        company.getEmployees().remove(e);
        this.transportCompanyRepository.save(company);

        this.employeeRepository.deleteCompany(companyId);

        return this.modelMapper.map(company, TransportCompanyDtoResponse.class);
    }
    // #region COMPANY EMPLOYEE

    private TransportCompany findTransportCompanyByIdOrThrow(int companyId){
        return this.transportCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));
    }

    private Client findClientByIdOrThrow(int clientId){
        return this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private TransportCompanyRevenue findTransportCompanyRevenueByIdOrThrow(int revenueId){
        return this.transportCompanyRevenueRepository.findById(revenueId)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));
    }

    private Vehicle findVehicleByIdOrThrow(int vehicleId){
        return this.vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Employee findEmployeeByIdOrThrow(int employeeId){
        return this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
