package ru.mrslyredfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.validation.Valid;

import ru.mrslyredfox.controller.repr.FileDescriptorRepr;
import ru.mrslyredfox.controller.repr.LessonRepr;
import ru.mrslyredfox.error.NotFoundException;
import ru.mrslyredfox.persist.repo.FileDescriptionRepository;
import ru.mrslyredfox.service.serviceInterface.FileDescriptorService;

@Controller
public class FileDescriptorController {

    private final FileDescriptionRepository fileDescriptionRepository;
    private final FileDescriptorService fileDescriptorService;

    @Autowired
    public FileDescriptorController(FileDescriptionRepository fileDescriptionRepository,FileDescriptorService fileDescriptorService) {
        this.fileDescriptionRepository = fileDescriptionRepository;
        this.fileDescriptorService=fileDescriptorService;
    }
//--------------------------------------------------------------------
    //загрузка файлов
    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
//--------------------------------------------------------
//Редактор предыдущей страницы – изменить имя файла
    @GetMapping("/files")
    public String editorFileName(Model model){
        model.addAttribute("activePage", "Files");
        model.addAttribute("files", fileDescriptorService.findAll());
        return "files";
    }

//Форма загрузки файла – указываем путь, по которому сохраняем файл
//    @GetMapping("/file_upload_form")
//    public String saveFile(Model model){
//        model.addAttribute("activePage","FileUploadForm");
//        return "file_upload_form";
//    }
@GetMapping("/file/{id}/edit")
public String adminEditFile(Model model, @PathVariable("id") Long id) {
    model.addAttribute("edit", true);
    model.addAttribute("activePage", "Files");
    model.addAttribute("file", fileDescriptorService.findById(id).orElseThrow(NotFoundException::new));
    return "file_name_editor";
}

    @GetMapping("/file/create")
    public String adminCreateFile(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Files");
        model.addAttribute("file", new FileDescriptorRepr());
        return "file_name_editor";
    }

    @PostMapping("/file")
    public String adminUpsertFile(@Valid FileDescriptorRepr fileDescriptorRepr, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Files");

        if (bindingResult.hasErrors()) {
            return "file_name_editor";
        }

        fileDescriptorService.save(fileDescriptorRepr);
        return "redirect:/files";
    }

    @DeleteMapping("/file/{id}/delete")
    public String adminDeleteFile(Model model, @PathVariable("id") Long id) {
        fileDescriptorService.deleteById(id);
        return "redirect:/files";
    }


}
