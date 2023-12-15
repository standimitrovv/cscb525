package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.client.ClientDto;
import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.dto.revenue.TransportCompanyRevenueDto;
import com.cscb525.project.dto.revenue.TransportCompanyRevenueDtoResponse;
import com.cscb525.project.dto.shipment.ShipmentDto;
import com.cscb525.project.dto.transportCompany.TransportCompanyDto;
import com.cscb525.project.dto.transportCompany.TransportCompanyDtoResponse;
import com.cscb525.project.dto.vehicle.VehicleDto;
import com.cscb525.project.exception.client.ClientNotFoundException;
import com.cscb525.project.exception.employee.EmployeeNotFoundException;
import com.cscb525.project.exception.revenue.RevenueNotFoundException;
import com.cscb525.project.exception.shipment.ShipmentNotFoundException;
import com.cscb525.project.exception.transportCompany.*;
import com.cscb525.project.exception.vehicle.VehicleNotFoundException;
import com.cscb525.project.model.client.Client;
import com.cscb525.project.model.employee.Employee;
import com.cscb525.project.model.revenue.TransportCompanyRevenue;
import com.cscb525.project.model.shipment.CargoType;
import com.cscb525.project.model.shipment.PaymentStatus;
import com.cscb525.project.model.shipment.Shipment;
import com.cscb525.project.model.transportCompany.FilterType;
import com.cscb525.project.model.transportCompany.SortType;
import com.cscb525.project.model.transportCompany.SortingAndFilteringCriteria;
import com.cscb525.project.model.transportCompany.TransportCompany;
import com.cscb525.project.model.vehicle.Vehicle;
import com.cscb525.project.repository.*;
import com.cscb525.project.service.TransportCompanyService;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.cscb525.project.exception.ExceptionTextMessages.*;

@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

    private final TransportCompanyRepository transportCompanyRepository;

    private final ClientRepository clientRepository;

    private final TransportCompanyRevenueRepository transportCompanyRevenueRepository;

    private final VehicleRepository vehicleRepository;

    private final EmployeeRepository employeeRepository;

    private final ShipmentRepository shipmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TransportCompanyServiceImpl(
            TransportCompanyRepository transportCompanyRepository,
            ClientRepository clientRepository,
            TransportCompanyRevenueRepository transportCompanyRevenueRepository,
            VehicleRepository vehicleRepository,
            EmployeeRepository employeeRepository,
            ShipmentRepository shipmentRepository
    ){
        this.transportCompanyRepository = transportCompanyRepository;
        this.clientRepository = clientRepository;
        this.transportCompanyRevenueRepository = transportCompanyRevenueRepository;
        this.vehicleRepository = vehicleRepository;
        this.employeeRepository = employeeRepository;
        this.shipmentRepository = shipmentRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<TransportCompanyDtoResponse> getAllTransportCompanies(SortType sortType,
                                                                      SortingAndFilteringCriteria sortBy,
                                                                      FilterType filterType,
                                                                      SortingAndFilteringCriteria filterBy,
                                                                      String companyNameToFilterBy,
                                                                      String revenueToFilterBy
    ){
        // Returns the list of transport companies WITHOUT any sorting or filtering applied
        if((sortBy == null || sortBy == SortingAndFilteringCriteria.NONE) && (filterBy == null || filterBy == SortingAndFilteringCriteria.NONE)){
            return convertToCompanyDtoResponseList(this.transportCompanyRepository.findAll());
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;

        if(sortType == SortType.DESC){
            sortDirection = Sort.Direction.DESC;
        }

        if(sortBy != SortingAndFilteringCriteria.NONE && filterBy != SortingAndFilteringCriteria.NONE) {
            TransportCompanyServiceImplSortingAndFilteringHelper helper = new TransportCompanyServiceImplSortingAndFilteringHelper(
                    filterType,
                    sortBy,
                    sortDirection,
                    transportCompanyRepository
            );

            // filtered (Where) by NAME
            if (filterBy == SortingAndFilteringCriteria.NAME) {

                return convertToCompanyDtoResponseList(helper.filterByNameAndSort(companyNameToFilterBy));
            } else { // filtered (where) by REVENUE
                final double revenue = Double.parseDouble(revenueToFilterBy);

                return convertToCompanyDtoResponseList(helper.filterByRevenueAndSort(revenue));
            }
        }

        if(filterBy != SortingAndFilteringCriteria.NONE){
            TransportCompanyServiceImplSortingAndFilteringHelper helper = new TransportCompanyServiceImplSortingAndFilteringHelper(
                    filterType,
                    transportCompanyRepository
            );

            if(filterBy == SortingAndFilteringCriteria.NAME){
                return convertToCompanyDtoResponseList(helper.filterByName(companyNameToFilterBy));
            } else {
                double revenue = Double.parseDouble(revenueToFilterBy);

                return convertToCompanyDtoResponseList(helper.filterByRevenue(revenue));
            }
        }

        return convertToCompanyDtoResponseList(
                new TransportCompanyServiceImplSortingAndFilteringHelper(
                        sortBy,
                        transportCompanyRepository
                ).sortOrReturnAll()
        );
    }

    private List<TransportCompanyDtoResponse> convertToCompanyDtoResponseList(List<TransportCompany> tcl){
        return tcl
                .stream()
                .map(c -> this.modelMapper.map(c, TransportCompanyDtoResponse.class))
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
    }

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

    // #region COMPANY SHIPMENT
    public TransportCompanyDtoResponse addShipment(int companyId, int employeeId, int clientId, int vehicleId, ShipmentDto shipmentDto){
        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Employee employee = findEmployeeByIdOrThrow(employeeId);

        Client client = findClientByIdOrThrow(clientId);

        Vehicle vehicle = findVehicleByIdOrThrow(vehicleId);

        CargoType cargoType = shipmentDto.getCargoType();
        double cargoWeight = shipmentDto.getCargoWeight();

        validateShipment(vehicle, company, client, employee, cargoType, cargoWeight);

        Shipment tempShipment = new Shipment();
        tempShipment.setDeparturePoint(shipmentDto.getDeparturePoint());
        tempShipment.setDestination(shipmentDto.getDestination());
        tempShipment.setDepartureDate(shipmentDto.getDepartureDate());
        tempShipment.setArrivalDate(shipmentDto.getArrivalDate());
        tempShipment.setPrice(shipmentDto.getPrice());
        tempShipment.setCargoWeight(cargoWeight);
        tempShipment.setCargoType(cargoType);
        tempShipment.setCompany(company);
        tempShipment.setEmployee(employee);
        tempShipment.setClient(client);
        tempShipment.setVehicle(vehicle);

        Shipment shipment = this.shipmentRepository.save(tempShipment);

        company.getShipments().add(shipment);
        return this.modelMapper.map(this.transportCompanyRepository.save(company), TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse updateShipmentPaymentStatus(int companyId, int employeeId, int clientId, int vehicleId, int shipmentId, PaymentStatus paymentStatus){

        TransportCompany company = findTransportCompanyByIdOrThrow(companyId);

        Employee employee = findEmployeeByIdOrThrow(employeeId);

        Client client = findClientByIdOrThrow(clientId);

        Vehicle vehicle = findVehicleByIdOrThrow(vehicleId);

        validateShipment(vehicle, company, client, employee, null, 0);

        Shipment shipment = findShipmentByIdOrThrow(shipmentId);

        if(!shipment.getCompany().equals(company)){
            throw new CompanyShipmentNotFoundException(COMPANY_SHIPMENT_NOT_FOUND);
        }

        if(paymentStatus == null){
            throw new ShipmentPaymentStatusNotDefinedException(SHIPMENT_PAYMENT_STATUS_NOT_DEFINED);
        }

        shipment.setPaymentStatus(paymentStatus);

        this.shipmentRepository.save(shipment);

        return this.modelMapper.map(company, TransportCompanyDtoResponse.class);
    }

    private void validateShipment(Vehicle vehicle,
                                  TransportCompany company,
                                  Client client,
                                  Employee employee,
                                  @Nullable CargoType cargoType,
                                  double cargoWeight
    ) {
        if(vehicle.getCompany() == null || vehicle.getCompany().getId() != company.getId()) {
            throw new CompanyVehicleNotFoundException(COMPANY_VEHICLE_NOT_FOUND);
        }

        boolean isCompanyClient = client.getCompanies().contains(company);

        if(!isCompanyClient) {
            throw new CompanyClientNotFoundException(COMPANY_CLIENT_NOT_FOUND);
        }

        if(employee.getCompany() == null || employee.getCompany().getId() != company.getId()){
            throw new CompanyEmployeeNotFoundException(COMPANY_EMPLOYEE_NOT_FOUND);
        }

        if(cargoType == CargoType.GOODS && cargoWeight <= 0 ){
            throw new CargoWeightNotDefinedException(CARGO_WEIGHT_NOT_DEFINED);
        }
    }
    // #endregion COMPANY SHIPMENT

    private TransportCompany findTransportCompanyByIdOrThrow(int companyId){
        return this.transportCompanyRepository.findById(companyId)
                .orElseThrow(() -> new TransportCompanyNotFoundException(TRANSPORT_COMPANY_NOT_FOUND));
    }

    private Client findClientByIdOrThrow(int clientId){
        return this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
    }

    private TransportCompanyRevenue findTransportCompanyRevenueByIdOrThrow(int revenueId){
        return this.transportCompanyRevenueRepository.findById(revenueId)
                .orElseThrow(() -> new RevenueNotFoundException(REVENUE_NOT_FOUND));
    }

    private Vehicle findVehicleByIdOrThrow(int vehicleId){
        return this.vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(VEHICLE_NOT_FOUND));
    }

    private Employee findEmployeeByIdOrThrow(int employeeId){
        return this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
    }

    private Shipment findShipmentByIdOrThrow(int shipmentId){
        return this.shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ShipmentNotFoundException(SHIPMENT_NOT_FOUND));
    }
}
