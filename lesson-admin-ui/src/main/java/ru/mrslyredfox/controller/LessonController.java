package ru.mrslyredfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import ru.mrslyredfox.controller.repr.FileDescriptorRepr;
import ru.mrslyredfox.controller.repr.LessonRepr;
import ru.mrslyredfox.controller.repr.UserRepr;
import ru.mrslyredfox.error.NotFoundException;
import ru.mrslyredfox.persist.repo.LessonRepository;
import ru.mrslyredfox.service.serviceInterface.FileDescriptorService;
import ru.mrslyredfox.service.serviceInterface.LessonService;


@Controller
public class LessonController {

    private final LessonRepository lessonRepository;

    private final LessonService lessonService;

    private final FileDescriptorService fileDescriptorService;

    @Autowired
    public LessonController(LessonRepository lessonRepository, LessonService lessonService,FileDescriptorService fileDescriptorService) {
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
        this.fileDescriptorService = fileDescriptorService;
    }

//Редактор предыдущей страницы – можем менять название урока и менять файлы
    @GetMapping("/lessons")
    public String editorLessonAndFileName(Model model){
        model.addAttribute("activePage", "Lesson");
        model.addAttribute("lessons", lessonService.findAll());
        return "lessons";
    }

    @GetMapping("/lesson/{id}/edit")
    public String adminEditLesson(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Lessons");
        model.addAttribute("lesson", lessonService.findById(id).orElseThrow(NotFoundException::new));
        return "lesson_editor";
    }

    @GetMapping("/lesson/create")
    public String adminCreateLesson(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Lessons");
        model.addAttribute("lesson", new LessonRepr());
        return "lesson_editor";
    }

    @PostMapping("/lesson")
    public String adminUpsertLesson(@Valid LessonRepr lesson, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Lessons");

        if (bindingResult.hasErrors()) {
            return "lesson_editor";
        }

        lessonService.save(lesson);
        return "redirect:/lessons";
    }

    @DeleteMapping("/lesson/{id}/delete")
    public String adminDeleteLesson(Model model, @PathVariable("id") Long id) {
        lessonService.delete(id);
        fileDescriptorService.deleteById(id);
        return "redirect:/lessons";
    }

}
