package com.cscb525.project.service.implementation;

import com.cscb525.project.model.transportCompany.FilterType;
import com.cscb525.project.model.transportCompany.SortingAndFilteringCriteria;
import com.cscb525.project.model.transportCompany.TransportCompany;
import com.cscb525.project.repository.TransportCompanyRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public class TransportCompanyServiceImplSortingAndFilteringHelper {
    private final FilterType filterType;
    private final SortingAndFilteringCriteria sortBy;
    private final Sort.Direction sortDirection;
    private final TransportCompanyRepository transportCompanyRepository;

    protected TransportCompanyServiceImplSortingAndFilteringHelper(FilterType filterType,
                                                                   TransportCompanyRepository tcr){
        this.filterType = filterType;
        this.sortBy = SortingAndFilteringCriteria.NONE;
        this.sortDirection = Sort.Direction.ASC;
        this.transportCompanyRepository = tcr;
    }

    protected TransportCompanyServiceImplSortingAndFilteringHelper(SortingAndFilteringCriteria sortBy,
                                                                   TransportCompanyRepository tcr){
        this.filterType = null;
        this.sortBy = sortBy;
        this.sortDirection = Sort.Direction.ASC;
        this.transportCompanyRepository = tcr;
    }

    protected TransportCompanyServiceImplSortingAndFilteringHelper(FilterType filterType,
                                                                   SortingAndFilteringCriteria sortBy,
                                                                   Sort.Direction sortDirection,
                                                                   TransportCompanyRepository tcr
    ){
        this.filterType = filterType;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
        this.transportCompanyRepository = tcr;
    }

    protected List<TransportCompany> filterByNameAndSort(String companyName) {
        if(this.sortBy == SortingAndFilteringCriteria.NAME){
            return this.transportCompanyRepository.getAllTransportCompaniesByNameAndNameOrdered(companyName, Sort.by(this.sortDirection, "name"));
        } else if (this.sortBy == SortingAndFilteringCriteria.REVENUE){
            return this.sortDirection == Sort.Direction.ASC ?
                    this.transportCompanyRepository.getAllTransportCompaniesByNameAndRevenueOrderedASC(companyName) :
                    this.transportCompanyRepository.getAllTransportCompaniesByNameAndRevenueOrderedDESC(companyName);
        } else {
            throw new RuntimeException("SortBy is not initialized in the constructor");
        }
    }

    protected List<TransportCompany> filterByRevenueAndSort(double revenue) {
        final boolean isSortingASC = sortDirection == Sort.Direction.ASC;

        if(this.filterType == null) {
            throw new RuntimeException("FilterType is not initialized in the constructor");
        }

        if(this.sortBy == SortingAndFilteringCriteria.NAME) {

            return switch(this.filterType){
                case EQ -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueEqualToAndCompanyNameOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueEqualToAndCompanyNameOrderedDESC(revenue);
                case LT -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanAndCompanyNameOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanAndCompanyNameOrderedDESC(revenue);
                case LTOEQ -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanOrEQToAndCompanyNameOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanOrEQToAndCompanyNameOrderedDESC(revenue);
                case MT -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanAndCompanyNameOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanAndCompanyNameOrderedDESC(revenue);
                case MTOEQ -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanOrEQToAndCompanyNameOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanOrEQToAndCompanyNameOrderedDESC(revenue);
            };
        } else if (this.sortBy == SortingAndFilteringCriteria.REVENUE){
            return switch(this.filterType){
                case EQ -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueEqualToAndRevenueOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueEqualToAndRevenueOrderedDESC(revenue);
                case LT -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanAndRevenueOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanAndRevenueOrderedDESC(revenue);
                case LTOEQ -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanOrEQToAndRevenueOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanOrEQToAndRevenueOrderedDESC(revenue);
                case MT -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanAndRevenueOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanAndRevenueOrderedDESC(revenue);
                case MTOEQ -> isSortingASC ?
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanOrEQToAndRevenueOrderedASC(revenue) :
                        this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanOrEQToAndRevenueOrderedDESC(revenue);
            };
        } else {
            throw new RuntimeException("SortBy is not initialized in the constructor");
        }
    }

    protected List<TransportCompany> filterByName(String companyName) {
        return this.transportCompanyRepository.getAllTransportCompaniesByName(companyName);
    }

    protected List<TransportCompany> filterByRevenue(double revenue) {
        if(this.filterType == null) {
            throw new RuntimeException("FilterType is not initialized in the constructor");
        }

        return switch (this.filterType){
            case EQ -> this.transportCompanyRepository.getAllTransportCompaniesWithRevenueEqualTo(revenue);
            case LT -> this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThan(revenue);
            case LTOEQ -> this.transportCompanyRepository.getAllTransportCompaniesWithRevenueLessThanOrEQTo(revenue);
            case MT -> this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThan(revenue);
            case MTOEQ -> this.transportCompanyRepository.getAllTransportCompaniesWithRevenueMoreThanOrEQTo(revenue);
        };
    }

    protected List<TransportCompany> sortOrReturnAll(){
        if(this.sortBy == null || this.sortBy == SortingAndFilteringCriteria.NONE) {
            throw new RuntimeException("SortBy is not initialized in the constructor");
        }

        return switch (this.sortBy){
            case REVENUE -> this.sortDirection == Sort.Direction.ASC ?
                    this.transportCompanyRepository.getAllTransportCompaniesOrderedByRevenueASC() :
                    this.transportCompanyRepository.getAllTransportCompaniesOrderedByRevenueDESC();
            case NAME -> this.transportCompanyRepository.getAllTransportCompaniesOrderedByName(Sort.by(this.sortDirection, "name"));
            default -> this.transportCompanyRepository.findAll();
        };
    }
}
