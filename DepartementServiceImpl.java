/*
 * package com.example.gestion_pointagesemployes.Controllers;
 * 
 * import java.util.List;
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * 
 * import com.example.gestion_pointagesemployes.Models.Departement;
 * import
 * com.example.gestion_pointagesemployes.Repository.DepartementRepository;
 * 
 * @Service
 * public class DepartementServiceImpl implements DepartementService {
 * 
 * @Autowired
 * public DepartementRepository departementRepository;
 * 
 * @Override
 * public List<Departement> getAllDepartements() {
 * return departementRepository.getAllDepartements();
 * }
 * 
 * @Override
 * public void saveDepartement(Departement departement) {
 * this.departementRepository.save(departement);
 * }
 * 
 * @Override
 * public void deleteDepartementById(long id) {
 * 
 * this.departementRepository.deleteById(id);
 * }
 * 
 * @Override
 * public Departement getDepartementById(long id) {
 * Optional<Departement> optional = departementRepository.findById(id);
 * Departement departement = null;
 * if (optional.isPresent()) {
 * // departement = departement.get();
 * } else {
 * throw new RuntimeException(" Departement not found for id:: " + id);
 * }
 * return departement;
 * }
 * 
 * }
 */
